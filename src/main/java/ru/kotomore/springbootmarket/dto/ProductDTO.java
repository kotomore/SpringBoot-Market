package ru.kotomore.springbootmarket.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kotomore.springbootmarket.models.ProductType;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private ProductType productType;
    private String serialNumber;
    private String manufacturer;
    private double price;
    private int count;
}