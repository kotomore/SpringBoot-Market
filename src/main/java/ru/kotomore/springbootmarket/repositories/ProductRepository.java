package ru.kotomore.springbootmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotomore.springbootmarket.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
