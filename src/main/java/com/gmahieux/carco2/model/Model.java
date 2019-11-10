package com.gmahieux.carco2.model;

public class Model {
    private String brand;
    private String model;
    private String type;
    private String swag;

    public Model(String brand, String model, String type, String swag) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.swag = swag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSwag() {
        return swag;
    }

    public void setSwag(String swag) {
        this.swag = swag;
    }
}
