package ru.kotomore.springbootmarket.dto;

public class MonitorDTO extends ProductDTO{
    private double diagonal;

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }
}
