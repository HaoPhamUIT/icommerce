package vn.phh.order.dto;

import lombok.Data;
import vn.phh.kafka.message.ProductAvro;

import java.util.List;

@Data
public class OrderInfoDTO {

    private String id;

    private String customerId;

    private String fullName;

    private double amount;

    private String phone;

    private String address;

    private List<ProductAvro> products;

    private int orderStatus;
}
