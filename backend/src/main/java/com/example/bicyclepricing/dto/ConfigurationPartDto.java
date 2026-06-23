package com.example.bicyclepricing.dto;

public class ConfigurationPartDto {
    private Long partId;
    private Integer quantity;

    public ConfigurationPartDto() {
    }

    public ConfigurationPartDto(Long partId, Integer quantity) {
        this.partId = partId;
        this.quantity = quantity;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
