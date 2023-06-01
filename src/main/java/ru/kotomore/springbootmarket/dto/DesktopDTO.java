package ru.kotomore.springbootmarket.dto;

public class DesktopDTO extends ProductDTO{
    private String formFactor;

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
