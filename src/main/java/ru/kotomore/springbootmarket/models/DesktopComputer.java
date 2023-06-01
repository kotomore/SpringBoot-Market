package ru.kotomore.springbootmarket.models;

import jakarta.persistence.Entity;

@Entity
public class DesktopComputer extends Product {
    private FormFactor formFactor;

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public enum FormFactor {
        DESKTOP("Десктоп"),
        NETTOP("Неттоп"),
        MONOBLOCK("Моноблок");

        private final String displayName;

        FormFactor(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
