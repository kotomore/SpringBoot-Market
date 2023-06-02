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

    @Schema(description = "Серийный номер товара")
    @NotEmpty(message = "не должно быть пустым")
    private String serialNumber;

    @Schema(description = "Производитель")
    @NotEmpty(message = "не должно быть пустым")
    private String manufacturer;

    @Schema(description = "Цена", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "должно быть больше 1")
    private double price;

    @Schema(description = "Количество на складе", requiredMode = Schema.RequiredMode.REQUIRED)
    private int count;

    @Schema(hidden = true, description = "Размер диагонали экрана монитора")
    private double diagonalSize;

    @Schema(hidden = true, description = "Размер ноутбука")
    private int screenSize;

    @Schema(hidden = true, description = "Объем жесткого диска")
    private int capacity;

    @Schema(hidden = true, description = "Форм-фактор компьютера (DESKTOP, NETTOP, MONOBLOCK)")
    private DesktopFormFactor formFactor;
}
