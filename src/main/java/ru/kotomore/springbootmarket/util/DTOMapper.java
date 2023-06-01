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

    public ProductDTO mapToDTO(Product product) {
        ProductType productType = product.getProductType();
        switch (productType) {
            case DESKTOP:
                return modelMapper.map(product, DesktopDTO.class);
            case LAPTOP:
                return modelMapper.map(product, LaptopDTO.class);
            case MONITOR:
                return modelMapper.map(product, MonitorDTO.class);
            case HARD_DRIVE:
                return modelMapper.map(product, HardDriveDTO.class);
            default:
                throw new IllegalArgumentException("Unsupported product type: " + productType);
        }
    }
}
