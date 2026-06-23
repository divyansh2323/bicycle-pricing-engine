package com.example.bicyclepricing.service;

import com.example.bicyclepricing.dto.PartDto;
import com.example.bicyclepricing.dto.UpdatePartPriceRequest;
import com.example.bicyclepricing.entity.Part;
import com.example.bicyclepricing.entity.PriceHistory;
import com.example.bicyclepricing.exception.ResourceNotFoundException;
import com.example.bicyclepricing.repository.PartRepository;
import com.example.bicyclepricing.repository.PriceHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public PartService(PartRepository partRepository, PriceHistoryRepository priceHistoryRepository) {
        this.partRepository = partRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    public List<PartDto> findAll() {
        return partRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PartDto findById(Long id) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Part not found with id " + id));
        return mapToDto(part);
    }

    public PartDto create(PartDto dto) {
        Part part = new Part(dto.getName(), dto.getCategory(), dto.getPrice());
        return mapToDto(partRepository.save(part));
    }

    public PartDto update(Long id, PartDto dto) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Part not found with id " + id));
        part.setName(dto.getName());
        part.setCategory(dto.getCategory());
        part.setPrice(dto.getPrice());
        return mapToDto(partRepository.save(part));
    }

    public void delete(Long id) {
        partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Part not found with id " + id));
        partRepository.deleteById(id);
    }

    @Transactional
    public PartDto updatePrice(Long id, UpdatePartPriceRequest request) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Part not found with id " + id));

        BigDecimal oldPrice = part.getPrice();
        part.setPrice(request.getNewPrice());
        Part savedPart = partRepository.save(part);

        PriceHistory history = new PriceHistory(oldPrice, request.getNewPrice(), Instant.now());
        history.setPart(savedPart);
        priceHistoryRepository.save(history);

        return mapToDto(savedPart);
    }

    private PartDto mapToDto(Part part) {
        return new PartDto(part.getId(), part.getName(), part.getCategory(), part.getPrice());
    }
}
