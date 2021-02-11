package vn.phh.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Tracking {

    @Id
    private String id;

    private int actionKey;

    private String customerId;

    private String actionName;

    private String info;

    private LocalDateTime createdDate;

    private String createdBy;
}
