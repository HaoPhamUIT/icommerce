package vn.phh.order.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.phh.kafka.message.ProductAvro;
import vn.phh.order.dto.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class OrderInfo {

    @Id
    private String id;

    private String customerId;

    private String fullName;

    private double amount;

    private String phone;

    private String address;

    private List<ProductAvro> products;

    private LocalDateTime createdDate;

    private String createdBy;

    private int orderStatus;
}
