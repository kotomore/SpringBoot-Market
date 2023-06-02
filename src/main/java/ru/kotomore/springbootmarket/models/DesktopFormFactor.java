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

    public static DesktopFormFactor fromDisplayName(String displayName) {
        try {
            return DesktopFormFactor.valueOf(DesktopFormFactor.class, displayName);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Неопознанный тип настольного компьютера - " + displayName);
        }
    }
}
