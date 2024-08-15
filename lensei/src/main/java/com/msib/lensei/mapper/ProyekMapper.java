package com.msib.lensei.mapper;

import com.msib.lensei.dto.ProyekDTO;
import com.msib.lensei.entity.Proyek;
import com.msib.lensei.entity.ProyekLokasi;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProyekMapper {

    private final LokasiMapper lokasiMapper;

    public ProyekMapper(LokasiMapper lokasiMapper) {
        this.lokasiMapper = lokasiMapper;
    }

    public ProyekDTO toDto(Proyek proyek) {
        return ProyekDTO.builder()
                .id(proyek.getId())
                .namaProyek(proyek.getNamaProyek())
                .client(proyek.getClient())
                .tglMulai(proyek.getTglMulai())
                .tglSelesai(proyek.getTglSelesai())
                .pimpinanProyek(proyek.getPimpinanProyek())
                .keterangan(proyek.getKeterangan())
                .lokasiList(proyek.getProyekLokasi().stream()
                        .map(ProyekLokasi::getLokasi)
                        .map(lokasiMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Proyek toEntity(ProyekDTO proyekDto) {
        return Proyek.builder()
                .id(proyekDto.getId())
                .namaProyek(proyekDto.getNamaProyek())
                .client(proyekDto.getClient())
                .tglMulai(proyekDto.getTglMulai())
                .tglSelesai(proyekDto.getTglSelesai())
                .pimpinanProyek(proyekDto.getPimpinanProyek())
                .keterangan(proyekDto.getKeterangan())
                .build();
    }
}
