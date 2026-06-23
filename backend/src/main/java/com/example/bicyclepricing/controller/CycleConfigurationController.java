package com.example.bicyclepricing.controller;

import com.example.bicyclepricing.dto.CycleConfigurationDto;
import com.example.bicyclepricing.dto.CreateConfigurationRequest;
import com.example.bicyclepricing.dto.PartPriceBreakdownDto;
import com.example.bicyclepricing.service.CycleConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configurations")
@CrossOrigin(origins = "*")
public class CycleConfigurationController {

    private final CycleConfigurationService configurationService;

    public CycleConfigurationController(CycleConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public List<CycleConfigurationDto> getAllConfigurations() {
        return configurationService.findAll();
    }

    @GetMapping("/{id}")
    public CycleConfigurationDto getConfiguration(@PathVariable Long id) {
        return configurationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CycleConfigurationDto> createConfiguration(@RequestBody CreateConfigurationRequest request) {
        CycleConfigurationDto created = configurationService.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public CycleConfigurationDto updateConfiguration(@PathVariable Long id, @RequestBody CreateConfigurationRequest request) {
        return configurationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfiguration(@PathVariable Long id) {
        configurationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/breakdown")
    public List<PartPriceBreakdownDto> getBreakdown(@PathVariable Long id) {
        return configurationService.getPriceBreakdown(id);
    }
}
