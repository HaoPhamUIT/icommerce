package vn.phh.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDTO {

    private String id;

    private String url;

    private String image;

    private int priority;

    private boolean status;

    private LocalDateTime createdDate;

}
