package com.gmahieux.carco2.model;

import java.math.BigDecimal;

public class Emissions {
    private BigDecimal nox;
    private Integer co2;

    public Emissions( Integer co2,BigDecimal nox) {
        this.nox = nox;
        this.co2 = co2;
    }

    public BigDecimal getNox() {
        return nox;
    }

    public void setNox(BigDecimal nox) {
        this.nox = nox;
    }

    public Integer getCo2() {
        return co2;
    }

    public void setCo2(Integer co2) {
        this.co2 = co2;
    }

    @Override
    public String toString() {
        return "co2=" + co2 + ", nox=" + nox ;
    }
}
