package vn.phh.authentication.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String id;
//
//    private String username;

    private String socialType;

    private String socialKey;

    private String deviceId;
}
