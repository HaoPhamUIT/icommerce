package vn.phh.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    @TextIndexed(weight=4)
    private String name;

    @TextIndexed(weight=1)
    private double price;

    @TextIndexed(weight=3)
    private String brand;

    @TextIndexed(weight=4)
    private String colour;

    private List<String> images;

    private int stock;

    private LocalDateTime createdDate;

    private String createdBy;
}
