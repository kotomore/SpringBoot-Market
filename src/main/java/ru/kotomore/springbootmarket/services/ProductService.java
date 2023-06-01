package ru.kotomore.springbootmarket.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;
import ru.kotomore.springbootmarket.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
    }

    public List<Product> getAllProductsByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
