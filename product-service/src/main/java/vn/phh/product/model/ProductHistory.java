package vn.phh.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ProductHistory {

    @Id
    private String id;

    private String productId;;

    private double price;

    private LocalDateTime createdDate;

    private String createdBy;
}
