package ru.kotomore.springbootmarket.dto;

import ru.kotomore.springbootmarket.models.DesktopFormFactor;

public class RequestProductDTO {

    private String serialNumber;
    private String manufacturer;
    private double price;
    private int count;
    private double diagonalSize;
    private int screenSize;
    private int capacity;
    private DesktopFormFactor formFactor;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(double diagonalSize) {
        this.diagonalSize = diagonalSize;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public DesktopFormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(DesktopFormFactor formFactor) {
        this.formFactor = formFactor;
    }
}