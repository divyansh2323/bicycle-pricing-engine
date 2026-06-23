package com.example.bicyclepricing.dto;

import java.math.BigDecimal;

public class PartPriceBreakdownDto {
    private Long partId;
    private String partName;
    private String category;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;

    public PartPriceBreakdownDto() {
    }

    public PartPriceBreakdownDto(Long partId, String partName, String category, BigDecimal unitPrice, Integer quantity, BigDecimal totalPrice) {
        this.partId = partId;
        this.partName = partName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
