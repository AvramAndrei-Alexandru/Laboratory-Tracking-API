package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Tokens;
import com.andrei.laboratory_tracking_api.repository.TokenRepository;
import com.andrei.laboratory_tracking_api.service.contracts.TokenService;
import com.andrei.laboratory_tracking_api.util.TokenGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl extends AbstractBaseServiceImpl<Tokens, Integer> implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        super(tokenRepository);
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Tokens createToken(String email) {
        if(!tokenRepository.existsByEmail(email)) {
            Tokens tokens = Tokens.builder()
                    .email(email)
                    .token(TokenGenerator.generateToken())
                    .build();
            return tokenRepository.save(tokens);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return tokenRepository.existsByEmail(email);
    }

    @Override
    public Tokens getByEmail(String email) {
        if (existsByEmail(email)){
            return tokenRepository.getByEmail(email);
        }
       return null;
    }
}
