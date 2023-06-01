package ru.kotomore.springbootmarket.models;

import jakarta.persistence.Entity;

@Entity
public class Laptop extends Product {
    private ScreenSize screenSize;

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    public enum ScreenSize {
        INCH_13("13 дюймов"),
        INCH_14("14 дюймов"),
        INCH_15("15 дюймов"),
        INCH_17("17 дюймов");

        private final String displayName;

        ScreenSize(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
