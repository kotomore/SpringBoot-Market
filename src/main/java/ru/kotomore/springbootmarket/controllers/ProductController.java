package ru.kotomore.springbootmarket.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotomore.springbootmarket.dto.*;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;
import ru.kotomore.springbootmarket.services.ProductService;
import ru.kotomore.springbootmarket.util.DTOMapper;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final DTOMapper dtoMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, DTOMapper dtoMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ResponseEntity<? extends ProductDTO> addProduct(@RequestParam ProductType productType,
                                                           @RequestBody RequestProductDTO product) {

        if (productType == null) {
            return ResponseEntity.badRequest().build();
        }

        Product newProduct = modelMapper.map(product, Product.class);
        newProduct.setProductType(productType);

        Product savedProduct = productService.save(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.mapToDTO(savedProduct));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<? extends ProductDTO> updateProduct(@PathVariable Long productId,
                                                              @RequestBody RequestProductDTO product) {
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        Product savedProduct = modelMapper.map(product, Product.class);
        savedProduct.setId(productId);
        savedProduct.setProductType(existingProduct.getProductType());

        Product updatedProduct = productService.updateProduct(savedProduct);
        return ResponseEntity.ok().body(dtoMapper.mapToDTO(updatedProduct));
    }


    @GetMapping
    public ResponseEntity<List<? extends ProductDTO>> getAllProductsByType(
            @RequestParam(value = "type") ProductType productType) {

        List<Product> product = productService.getAllProductsByType(productType);

        List<? extends ProductDTO> productDTOS = product
                .stream()
                .map(dtoMapper::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<? extends ProductDTO> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapToDTO(product));
    }
}
