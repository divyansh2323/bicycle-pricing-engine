package com.example.bicyclepricing.dto;

import java.math.BigDecimal;
import java.util.List;

public class CycleConfigurationDto {
    private Long id;
    private String name;
    private List<ConfigurationPartDto> parts;
    private BigDecimal totalPrice;

    public CycleConfigurationDto() {
    }

    public CycleConfigurationDto(Long id, String name, List<ConfigurationPartDto> parts, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.parts = parts;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConfigurationPartDto> getParts() {
        return parts;
    }

    public void setParts(List<ConfigurationPartDto> parts) {
        this.parts = parts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
