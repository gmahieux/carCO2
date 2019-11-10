package com.gmahieux.carco2.model;

import java.math.BigDecimal;

public class Consumption{
    private BigDecimal urban;
    private BigDecimal extraUrban;
    private BigDecimal combined;

    public Consumption(BigDecimal urban, BigDecimal extraUrban, BigDecimal combined) {
        this.urban = urban;
        this.extraUrban = extraUrban;
        this.combined = combined;
    }

    public BigDecimal getUrban() {
        return urban;
    }

    public void setUrban(BigDecimal urban) {
        this.urban = urban;
    }

    public BigDecimal getExtraUrban() {
        return extraUrban;
    }

    public void setExtraUrban(BigDecimal extraUrban) {
        this.extraUrban = extraUrban;
    }

    public BigDecimal getCombined() {
        return combined;
    }

    public void setCombined(BigDecimal combined) {
        this.combined = combined;
    }
}
