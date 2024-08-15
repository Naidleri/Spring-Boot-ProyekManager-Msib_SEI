package com.msib.lensei.mapper;

import com.msib.lensei.dto.LokasiDTO;
import com.msib.lensei.entity.Lokasi;

public class LokasiMapper {
    public LokasiDTO toDto(Lokasi lokasi) {
        return LokasiDTO.builder()
        .id(lokasi.getId())
        .namaLokasi(lokasi.getNamaLokasi()) 
        .negara(lokasi.getNegara())
        .provinsi(lokasi.getProvinsi())
        .kota(lokasi.getKota())
        .build();
    }

    public Lokasi toEntity(LokasiDTO lokasiDto) {
        return Lokasi.builder()
                .id(lokasiDto.getId())
                .namaLokasi(lokasiDto.getNamaLokasi())
                .negara(lokasiDto.getNegara())
                .provinsi(lokasiDto.getProvinsi())
                .kota(lokasiDto.getKota())
                .build();
    }
}
