package vn.phh.authentication.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String id;

    private int socialNetworkType;

    private String socialNetworkKey;
}
