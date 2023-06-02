package ru.kotomore.springbootmarket.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotomore.springbootmarket.dto.*;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;

@Component
public class DTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public DTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Преобразовать объект Product в DTO в зависимости от типа товара
     *
     * @param product     Product который необходимо преобразовать
     * @return DTO объект наследованный от ProductDTO
     */
    public ProductDTO mapToDTO(Product product) {
        ProductType productType = product.getProductType();
        return switch (productType) {
            case DESKTOP -> modelMapper.map(product, DesktopDTO.class);
            case LAPTOP -> modelMapper.map(product, LaptopDTO.class);
            case MONITOR -> modelMapper.map(product, MonitorDTO.class);
            case HARD_DRIVE -> modelMapper.map(product, HardDriveDTO.class);
        };
    }
}
