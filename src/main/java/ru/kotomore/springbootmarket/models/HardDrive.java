package ru.kotomore.springbootmarket.models;

import jakarta.persistence.Entity;

@Entity
public class HardDrive extends Product {
    private int capacity; // Объем жесткого диска

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
