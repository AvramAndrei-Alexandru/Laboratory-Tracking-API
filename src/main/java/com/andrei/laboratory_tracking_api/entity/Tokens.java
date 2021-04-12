package com.andrei.laboratory_tracking_api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tokens")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Tokens extends AbstractEntity{
    @Column(name = "email", unique = true)
    @NotNull
    @Email
    private String email;

    @NotNull
    @Column(name = "token", length = 128)
    @Length(min = 128, max = 128)
    private String token;
}
