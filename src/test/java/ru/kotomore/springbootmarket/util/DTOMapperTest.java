package ru.kotomore.springbootmarket.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import ru.kotomore.springbootmarket.dto.*;
import ru.kotomore.springbootmarket.models.DesktopFormFactor;
import ru.kotomore.springbootmarket.models.Product;
import ru.kotomore.springbootmarket.models.ProductType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DTOMapperTest {
    private DTOMapper dtoMapper;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dtoMapper = new DTOMapper(modelMapper);
    }

    @Test
    public void testMapToDTO_DesktopProduct() {
        Product product = createProduct();
        product.setProductType(ProductType.DESKTOP);
        DesktopDTO desktopDTO = new DesktopDTO();
        when(modelMapper.map(product, DesktopDTO.class)).thenReturn(desktopDTO);

        ProductDTO productDTO = dtoMapper.mapToDTO(product);

        assertNotNull(productDTO);
        assertTrue(productDTO instanceof DesktopDTO);
        assertEquals(desktopDTO, productDTO);
        verify(modelMapper, times(1)).map(product, DesktopDTO.class);
    }

    @Test
    public void testMapToDTO_LaptopProduct() {
        Product product = createProduct();
        product.setProductType(ProductType.LAPTOP);
        LaptopDTO laptopDTO = new LaptopDTO();
        when(modelMapper.map(product, LaptopDTO.class)).thenReturn(laptopDTO);

        ProductDTO productDTO = dtoMapper.mapToDTO(product);

        assertNotNull(productDTO);
        assertTrue(productDTO instanceof LaptopDTO);
        assertEquals(laptopDTO, productDTO);
        verify(modelMapper, times(1)).map(product, LaptopDTO.class);
    }

    @Test
    public void testMapToDTO_MonitorProduct() {
        Product product = createProduct();
        product.setProductType(ProductType.MONITOR);
        MonitorDTO monitorDTO = new MonitorDTO();
        when(modelMapper.map(product, MonitorDTO.class)).thenReturn(monitorDTO);

        ProductDTO productDTO = dtoMapper.mapToDTO(product);

        assertNotNull(productDTO);
        assertTrue(productDTO instanceof MonitorDTO);
        assertEquals(monitorDTO, productDTO);
        verify(modelMapper, times(1)).map(product, MonitorDTO.class);
    }

    @Test
    public void testMapToDTO_HardDriveProduct() {
        Product product = createProduct();
        product.setProductType(ProductType.HARD_DRIVE);
        HardDriveDTO hardDriveDTO = new HardDriveDTO();
        when(modelMapper.map(product, HardDriveDTO.class)).thenReturn(hardDriveDTO);

        ProductDTO productDTO = dtoMapper.mapToDTO(product);

        assertNotNull(productDTO);
        assertTrue(productDTO instanceof HardDriveDTO);
        assertEquals(hardDriveDTO, productDTO);
        verify(modelMapper, times(1)).map(product, HardDriveDTO.class);
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setProductType(ProductType.DESKTOP);
        product.setFormFactor(DesktopFormFactor.DESKTOP);
        product.setCount(15);
        product.setPrice(10000);
        product.setSerialNumber("SerialNumber");
        product.setManufacturer("Manufacturer");
        return product;
    }
}
