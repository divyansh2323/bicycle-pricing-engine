package com.example.bicyclepricing.dto;

import java.math.BigDecimal;

public class UpdatePartPriceRequest {
    private BigDecimal newPrice;

    public UpdatePartPriceRequest() {
    }

    public UpdatePartPriceRequest(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }
}
