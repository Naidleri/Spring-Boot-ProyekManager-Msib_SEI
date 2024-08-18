package com.msib.lensei.service;

import com.msib.lensei.dto.LokasiDTO;
import com.msib.lensei.entity.Lokasi;
import com.msib.lensei.exception.ConflictException;
import com.msib.lensei.exception.ResourceNotFoundException;
import com.msib.lensei.mapper.LokasiMapper;
import com.msib.lensei.repository.LokasiRepository;
import com.msib.lensei.repository.ProyekLokasiRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LokasiService {

    private final LokasiRepository lokasiRepository;
    private final ProyekLokasiRepository proyekLokasiRepository;
    private final LokasiMapper lokasiMapper;

    public LokasiService(LokasiRepository lokasiRepository, ProyekLokasiRepository proyekLokasiRepository, LokasiMapper lokasiMapper) {
        this.lokasiRepository = lokasiRepository;
        this.proyekLokasiRepository = proyekLokasiRepository;
        this.lokasiMapper = lokasiMapper;
    }

    public LokasiDTO saveLokasi(LokasiDTO lokasiDto) {
        Lokasi lokasi = lokasiMapper.toEntity(lokasiDto);
        Lokasi savedLokasi = lokasiRepository.save(lokasi);
        return lokasiMapper.toDto(savedLokasi);
    }

    public List<LokasiDTO> getAllLokasi() {
        return lokasiRepository.findAll().stream()
                .map(lokasiMapper::toDto)
                .collect(Collectors.toList());
    }

    public LokasiDTO updateLokasi(Integer id, LokasiDTO lokasiDto) {
        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi dengan id " + id + " tidak ditemukan"));
        lokasi.setNamaLokasi(lokasiDto.getNamaLokasi());
        lokasi.setNegara(lokasiDto.getNegara());
        lokasi.setProvinsi(lokasiDto.getProvinsi());
        lokasi.setKota(lokasiDto.getKota());
        Lokasi updatedLokasi = lokasiRepository.save(lokasi);
        return lokasiMapper.toDto(updatedLokasi);
    }

    public void deleteLokasi(Integer id) {
        if (!lokasiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lokasi dengan id " + id + " tidak ditemukan");
        }

        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi dengan id " + id + " tidak ditemukan"));

        boolean isUsedInProyek = proyekLokasiRepository.existsByLokasi(lokasi);
        if (isUsedInProyek) {
            throw new ConflictException("Lokasi dengan id " + id + " tidak bisa dihapus karena masih terkait dengan proyek.");
        }

        lokasiRepository.delete(lokasi);
    }
}
