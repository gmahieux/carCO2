package com.gmahieux.carco2.model;

public class Car {
    private Model model;
    private String name;
    private String power;
    private Energy energy;
    private String weight;
    private Consumption consumption;
    private Emissions emissions;

    public Car(Model model, String name, String power, Energy energy, String weight, Consumption consumption, Emissions emissions) {
        this.model = model;
        this.name = name;
        this.power = power;
        this.energy = energy;
        this.weight = weight;
        this.consumption = consumption;
        this.emissions = emissions;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public Emissions getEmissions() {
        return emissions;
    }

    public void setEmissions(Emissions emissions) {
        this.emissions = emissions;
    }

    @Override
    public String toString() {
        return model.getBrand() + " " + name + " ( " + emissions + ")";
    }
}

