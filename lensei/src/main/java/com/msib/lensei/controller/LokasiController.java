package com.msib.lensei.controller;

import com.msib.lensei.dto.LokasiDTO;
import com.msib.lensei.service.LokasiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lokasi")

public class LokasiController {
    private final LokasiService lokasiService;

    public LokasiController(LokasiService lokasiService) {
        this.lokasiService = lokasiService;
    }

    @PostMapping
    public ResponseEntity<LokasiDTO> createLokasi(@RequestBody LokasiDTO lokasiDto) {
        LokasiDTO savedLokasi = lokasiService.saveLokasi(lokasiDto);
        return ResponseEntity.ok(savedLokasi);
    }

    @GetMapping
    public ResponseEntity<List<LokasiDTO>> getAllLokasi() {
        List<LokasiDTO> lokasiList = lokasiService.getAllLokasi();
        return ResponseEntity.ok(lokasiList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LokasiDTO> updateLokasi(@PathVariable Integer id, @RequestBody LokasiDTO lokasiDto) {
        LokasiDTO updatedLokasi = lokasiService.updateLokasi(id, lokasiDto);
        return ResponseEntity.ok(updatedLokasi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Integer id) {
        lokasiService.deleteLokasi(id);
        return ResponseEntity.noContent().build();
    }
}
