package com.msib.lensei.service;

import com.msib.lensei.dto.ProyekDTO;
import com.msib.lensei.entity.Lokasi;
import com.msib.lensei.entity.Proyek;
import com.msib.lensei.entity.ProyekLokasi;
import com.msib.lensei.exception.ResourceNotFoundException;
import com.msib.lensei.mapper.ProyekMapper;
import com.msib.lensei.repository.LokasiRepository;
import com.msib.lensei.repository.ProyekLokasiRepository;
import com.msib.lensei.repository.ProyekRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyekService {
    private final ProyekRepository proyekRepository;
    private final LokasiRepository lokasiRepository;
    private final ProyekLokasiRepository proyekLokasiRepository;
    private final ProyekMapper proyekMapper;

    public ProyekService(ProyekRepository proyekRepository, LokasiRepository lokasiRepository,
            ProyekLokasiRepository proyekLokasiRepository, ProyekMapper proyekMapper) {
        this.proyekRepository = proyekRepository;
        this.lokasiRepository = lokasiRepository;
        this.proyekLokasiRepository = proyekLokasiRepository;
        this.proyekMapper = proyekMapper;
    }

    @Transactional
    public ProyekDTO saveProyek(ProyekDTO proyekDto) {
        Proyek proyek = proyekMapper.toEntity(proyekDto);
        Proyek savedProyek = proyekRepository.save(proyek);
        List<ProyekLokasi> proyekLokasiList = proyekDto.getLokasiList().stream()
                .map(lokasiDto -> {
                    Lokasi lokasi = lokasiRepository.findById(lokasiDto.getId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Lokasi dengan id " + lokasiDto.getId() + "tidak ditemukan"));
                    return new ProyekLokasi(null, savedProyek, lokasi);
                })
                .collect(Collectors.toList());

        proyekLokasiRepository.saveAll(proyekLokasiList);
        savedProyek.setProyekLokasi(proyekLokasiList);
        return proyekMapper.toDto(savedProyek);
    }

    public List<ProyekDTO> getAllProyek() {
        return proyekRepository.findAll().stream()
                .map(proyekMapper::toDto)
                .collect(Collectors.toList());
    }
}
