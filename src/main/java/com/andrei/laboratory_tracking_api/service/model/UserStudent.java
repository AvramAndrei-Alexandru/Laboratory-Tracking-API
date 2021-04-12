package com.andrei.laboratory_tracking_api.service.model;

import lombok.Data;

@Data
public class UserStudent {
    private String email;
    private String password;
    private String fullName;
    private String token;
    private int groupID;
    private String hobby;
}
