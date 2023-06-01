package ru.kotomore.springbootmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);
}
