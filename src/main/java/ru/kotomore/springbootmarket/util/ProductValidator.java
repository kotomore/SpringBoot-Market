package ru.kotomore.springbootmarket.util;

import org.springframework.stereotype.Component;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;

import java.util.List;

@Component
public class ProductValidator {

    public void validate(Product product) {
        ProductType productType = product.getProductType();

        switch (productType) {
            case DESKTOP -> {
                if (product.getFormFactor() == null) {
                    throw new IllegalArgumentException("Поле formFactor не может быть пустым");
                }
            }
            case LAPTOP -> {
                if (List.of(13, 14, 15, 17).contains(product.getScreenSize())) {
                    throw new IllegalArgumentException("Поле screenSize может быть 13, 14, 15 или 17");
                }
            }
            case MONITOR -> {
                if (product.getDiagonalSize() < 1) {
                    throw new IllegalArgumentException("Поле diagonalSize не может быть меньше 1");
                }
            }
            case HARD_DRIVE -> {
                if (product.getCapacity() < 1) {
                    throw new IllegalArgumentException("Поле capacity не может быть менее 1");
                }
            }
        }
    }
}
