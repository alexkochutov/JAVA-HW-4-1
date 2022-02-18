package ru.netology.domain;

public class Smartphone extends Product {
    private String manufacturer;

    public Smartphone() {
        super();
    }

    public Smartphone(int id, String name, int cost, String manufacturer) {
        super(id, name, cost);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean matches(String keyword) {
        if (!super.matches(keyword)) {
            return this.getManufacturer().contains(keyword);
        } else {
            return true;
        }
    }
}