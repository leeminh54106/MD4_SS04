package com.ra.session04.model.dto.request;

import com.ra.session04.validation.ProductNotExist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank
//    @ProductNotExist
    private String name;
    @Size(max=100)
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer stock;
}
