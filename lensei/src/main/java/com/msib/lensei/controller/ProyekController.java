package com.msib.lensei.controller;

import com.msib.lensei.dto.ProyekDTO;
import com.msib.lensei.service.ProyekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyek")

public class ProyekController {
    private final ProyekService proyekService;

    public ProyekController(ProyekService proyekService) {
        this.proyekService = proyekService;
    }

    @PostMapping
    public ResponseEntity<ProyekDTO> createProyek(@RequestBody ProyekDTO proyekDto) {
        ProyekDTO savedProyek = proyekService.saveProyek(proyekDto);
        return ResponseEntity.ok(savedProyek);
    }

    @GetMapping
    public ResponseEntity<List<ProyekDTO>> getAllProyek() {
        List<ProyekDTO> proyekList = proyekService.getAllProyek();
        return ResponseEntity.ok(proyekList);
    }
}
