package com.msib.lensei.repository;

import com.msib.lensei.entity.Lokasi;
import com.msib.lensei.entity.ProyekLokasi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Integer> {
    boolean existsByLokasi(Lokasi lokasi);
}
