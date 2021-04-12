package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.Tokens;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends AbstractBaseRepository<Tokens, Integer> {
    boolean existsByEmail(String email);
    Tokens getByEmail(String email);
}
