package ru.kotomore.springbootmarket.dto;

public class HardDriveDTO extends ProductDTO{
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
