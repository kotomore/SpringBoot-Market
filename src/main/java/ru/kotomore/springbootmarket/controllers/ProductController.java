package ru.kotomore.springbootmarket.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotomore.springbootmarket.dto.DesktopDTO;
import ru.kotomore.springbootmarket.dto.ErrorMessage;
import ru.kotomore.springbootmarket.dto.ProductDTO;
import ru.kotomore.springbootmarket.dto.RequestProductDTO;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;
import ru.kotomore.springbootmarket.services.ProductService;
import ru.kotomore.springbootmarket.util.DTOMapper;
import ru.kotomore.springbootmarket.util.ProductValidator;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final DTOMapper dtoMapper;
    private final ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, DTOMapper dtoMapper, ProductValidator productValidator) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.dtoMapper = dtoMapper;
        this.productValidator = productValidator;
    }

    @PostMapping
    @Operation(summary = "Добавление товара", description = """
            Добавляет товар. В зависимости от типа товара могут быть разные свойства:
                        
                "diagonalSize":double для мониторов
                "screenSize":integer для ноутбуков
                "formFactor":("DESKTOP", "NETTOP", "MONOBLOCK") для настольных компьютеров
                "capacity":integer для жестких дисков
                """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Товар добавлен",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DesktopDTO.class))),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<ProductDTO> addProduct(@RequestParam ProductType productType,
                                                           @Valid @RequestBody RequestProductDTO product) {

        Product newProduct = modelMapper.map(product, Product.class);
        newProduct.setProductType(productType);
        productValidator.validate(newProduct);

        Product savedProduct = productService.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.mapToDTO(savedProduct));
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Редактирование товара", description = """
            Изменяет поля товара. В зависимости от типа товара могут быть разные свойства:
                        
                "diagonalSize:double" для мониторов
                "screenSize:integer" для ноутбуков
                "formFactor:("DESKTOP", "NETTOP", "MONOBLOCK")" для настольных компьютеров
                "capacity:integer" для жестких дисков
                """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар обновлен",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DesktopDTO.class))),
            @ApiResponse(responseCode = "204", description = "Редактируемый товар не найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,
                                                              @RequestBody RequestProductDTO product) {

        Product existingProduct = productService.getProductById(productId);

        Product savedProduct = modelMapper.map(product, Product.class);
        savedProduct.setId(productId);
        savedProduct.setProductType(existingProduct.getProductType());
        productValidator.validate(savedProduct);

        Product updatedProduct = productService.updateProduct(savedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapToDTO(updatedProduct));
    }


    @GetMapping
    @Operation(summary = "Просмотр всех существующих товаров по типу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товары найдены",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DesktopDTO.class))),
            @ApiResponse(responseCode = "204", description = "Товаров не найдено",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Page<ProductDTO>> getAllProductsByType(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "type") ProductType productType) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productService.getAllProductsByType(productType, pageable);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            Page<ProductDTO> productDTOS = products.map(dtoMapper::mapToDTO);
            return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
        }
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Просмотр товара по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Товар найден",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DesktopDTO.class))),
            @ApiResponse(responseCode = "204", description = "Товар не найден",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {

        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapToDTO(product));
    }
}
