package com.msib.lensei.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proyek_lokasi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyekLokasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proyek_id", nullable = false)
    private Proyek proyek;

    @ManyToOne
    @JoinColumn(name = "lokasi_id", nullable = false)
    private Lokasi lokasi;
}