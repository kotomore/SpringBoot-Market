package ru.kotomore.springbootmarket.models;

public enum DesktopFormFactor {
    DESKTOP("Десктоп"),
    NETTOP("Неттоп"),
    MONOBLOCK("Моноблок");

    private final String displayName;

    DesktopFormFactor(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
