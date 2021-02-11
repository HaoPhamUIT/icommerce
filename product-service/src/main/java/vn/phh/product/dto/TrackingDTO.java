package vn.phh.product.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TrackingDTO {

    private String id;

    private int actionKey;

    private String actionName;

    private String customerId;

    private String info;

    private LocalDateTime createdDate;

    private String createdBy;
}
