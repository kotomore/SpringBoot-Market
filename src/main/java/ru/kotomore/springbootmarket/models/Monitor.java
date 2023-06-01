package ru.kotomore.springbootmarket.models;

import jakarta.persistence.Entity;

@Entity
public class Monitor extends Product {
    private double diagonalSize; // Диагональ экрана

    public double getDiagonalSize() {
        return diagonalSize;
    }

    public void setDiagonalSize(double diagonalSize) {
        this.diagonalSize = diagonalSize;
    }
}
