package vn.phh.authentication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class User {

    @Id
    private String id;

    private String username;

    private int socialNetworkType;

    private String socialNetworkKey;

    private String deviceId;

    private LocalDateTime createdDate;
}
