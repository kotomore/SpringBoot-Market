package ru.kotomore.springbootmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductType(ProductType productType, Pageable pageable);
}
