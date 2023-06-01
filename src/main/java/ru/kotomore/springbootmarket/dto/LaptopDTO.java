package ru.kotomore.springbootmarket.dto;

public class LaptopDTO extends ProductDTO{
    private int screenSize;

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
