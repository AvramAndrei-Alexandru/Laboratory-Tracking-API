package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Roles;
import com.andrei.laboratory_tracking_api.entity.User;
import com.andrei.laboratory_tracking_api.repository.AbstractBaseRepository;
import com.andrei.laboratory_tracking_api.repository.UserRepository;
import com.andrei.laboratory_tracking_api.service.contracts.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractBaseServiceImpl<User, Integer> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if(!existsByEmail(user.getEmail())) {
            Roles roles = Roles.builder()
                    .roleName("ROLE_STUDENT")
                    .id(2)
                    .build();
            user.setRole(roles);
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
