package ru.kotomore.springbootmarket.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kotomore.springbootmarket.models.DesktopFormFactor;

@Getter
@Setter
public class DesktopDTO extends ProductDTO {

    private DesktopFormFactor formFactor;
}
