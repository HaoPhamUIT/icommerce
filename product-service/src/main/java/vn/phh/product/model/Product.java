package vn.phh.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Product {

    @Id
    private String id;

    private String name;

    private double price;

    private String brand;

    private String colour;

    private List<String> images;

    private int stock;

    private LocalDateTime createdDate;

    private String createdBy;
}
