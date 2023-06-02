package ru.kotomore.springbootmarket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ru.kotomore.springbootmarket.models.DesktopFormFactor;

@Getter
@Setter
public class RequestProductDTO {

    @NotEmpty(message = "не должно быть пустым")
    private String serialNumber;
    @NotEmpty(message = "не должно быть пустым")
    private String manufacturer;
    @Min(value = 1, message = "должно быть больше 1")
    private double price;
    private int count;
    @Schema(hidden = true)
    private double diagonalSize;
    @Schema(hidden = true)
    private int screenSize;
    @Schema(hidden = true)
    private int capacity;
    @Schema(hidden = true)
    private DesktopFormFactor formFactor;
}
