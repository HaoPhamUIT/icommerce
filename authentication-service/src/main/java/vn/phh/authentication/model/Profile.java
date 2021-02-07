package vn.phh.authentication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Profile {

    @Id
    private String id;

    private String fullName;

    private String phone;

    private String address;

    private String email;

    private LocalDateTime createdDate;
}
