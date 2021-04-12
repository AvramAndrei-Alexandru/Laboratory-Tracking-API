package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.User;

public interface UserService extends AbstractBaseService<User, Integer> {
    User save(User user);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
