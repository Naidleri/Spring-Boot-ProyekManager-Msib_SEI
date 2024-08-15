package com.msib.lensei.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LokasiDTO {
    private Integer id;
    private String namaLokasi;
    private String negara;
    private String provinsi;
    private String kota;
}
