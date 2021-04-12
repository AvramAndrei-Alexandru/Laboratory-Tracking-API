package com.andrei.laboratory_tracking_api.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends AbstractEntity{
    @NotNull
    @Column(name = "role_name", unique = true)
    private String roleName;
}
