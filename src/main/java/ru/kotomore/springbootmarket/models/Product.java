package ru.kotomore.springbootmarket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProductType productType;
    private String serialNumber;
    private String manufacturer;
    private double price;
    private int count;
    private double diagonalSize;
    private int screenSize;
    private int capacity;
    private DesktopFormFactor formFactor;
}
