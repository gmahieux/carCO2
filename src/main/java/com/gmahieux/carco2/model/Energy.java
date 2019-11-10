package com.gmahieux.carco2.model;

public class Energy {
    private String energyType;
    private String hybrid;

    public Energy(String energyType, String hybrid) {
        this.energyType = energyType;
        this.hybrid = hybrid;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getHybrid() {
        return hybrid;
    }

    public void setHybrid(String hybrid) {
        this.hybrid = hybrid;
    }
}
