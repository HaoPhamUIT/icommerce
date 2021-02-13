package vn.phh.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;

    private String name;

    private double price;

    private String brand;

    private String colour;

    private List<String> images;

    private int numberProduct;

    private LocalDateTime createdDate;

    private String createdBy;

}
