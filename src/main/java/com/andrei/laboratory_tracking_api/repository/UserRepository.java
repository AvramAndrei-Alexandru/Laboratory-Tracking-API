package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractBaseRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
