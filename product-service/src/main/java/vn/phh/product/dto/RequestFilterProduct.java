package vn.phh.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFilterProduct {

    private String name;

    private double minPrice;

    private double maxPrice;

    private String brand;

    private String colour;

}
