package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Tokens;

public interface TokenService extends AbstractBaseService<Tokens, Integer>{
    Tokens createToken(String email);
    boolean existsByEmail(String email);
    Tokens getByEmail(String email);
}
