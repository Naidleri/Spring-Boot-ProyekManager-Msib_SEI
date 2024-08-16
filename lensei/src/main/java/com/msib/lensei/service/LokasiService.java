package com.msib.lensei.service;

import com.msib.lensei.dto.LokasiDTO;
import com.msib.lensei.entity.Lokasi;
import com.msib.lensei.mapper.LokasiMapper;
import com.msib.lensei.repository.LokasiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LokasiService {

    private final LokasiRepository lokasiRepository;
    private final LokasiMapper lokasiMapper;

    public LokasiService(LokasiRepository lokasiRepository, LokasiMapper lokasiMapper) {
        this.lokasiRepository = lokasiRepository;
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
}
