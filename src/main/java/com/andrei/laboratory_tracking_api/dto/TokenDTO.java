package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.Tokens;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
public class TokenDTO {

    private int id;
    @JsonProperty(required = true)
    @Email
    private String email;
    private String token;

    public static TokenDTO mapEntityToDTO(Tokens tokens) {
        return TokenDTO.builder()
                .id(tokens.getId())
                .token(tokens.getToken())
                .email(tokens.getEmail())
                .build();
    }
}
