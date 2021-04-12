package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.StudentAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.entity.Tokens;
import com.andrei.laboratory_tracking_api.dto.TokenDTO;
import com.andrei.laboratory_tracking_api.service.contracts.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/token")
@RequiredArgsConstructor
public class TokenRestController {

    private final TokenService tokenService;


    @TeacherAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TokenDTO>> getAllTokens() {
        List<TokenDTO> tokenDTOS = tokenService.findAll().stream()
                .map(TokenDTO::mapEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tokenDTOS);
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTokenByID(@PathVariable int id) {
        if(tokenService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @TeacherAccess
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TokenDTO> createToken(@Valid @RequestBody TokenDTO tokenDTO) {
        Tokens savedToken = tokenService.createToken(tokenDTO.getEmail());
        if(savedToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(TokenDTO.mapEntityToDTO(savedToken));
    }

}
