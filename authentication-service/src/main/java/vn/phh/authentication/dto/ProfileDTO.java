package vn.phh.authentication.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDTO {

    private String id;

    private String fullName;

    private String phone;

    private String address;

    private String email;

    private LocalDateTime createdDate;

}
