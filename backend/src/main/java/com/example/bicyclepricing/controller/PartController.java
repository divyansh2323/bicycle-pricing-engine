package com.example.bicyclepricing.controller;

import com.example.bicyclepricing.dto.PartDto;
import com.example.bicyclepricing.dto.UpdatePartPriceRequest;
import com.example.bicyclepricing.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin(origins = "*")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<PartDto> getAllParts() {
        return partService.findAll();
    }

    @GetMapping("/{id}")
    public PartDto getPart(@PathVariable Long id) {
        return partService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PartDto> createPart(@RequestBody PartDto dto) {
        PartDto created = partService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public PartDto updatePart(@PathVariable Long id, @RequestBody PartDto dto) {
        return partService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/price")
    public PartDto updatePartPrice(@PathVariable Long id, @RequestBody UpdatePartPriceRequest request) {
        return partService.updatePrice(id, request);
    }
}
