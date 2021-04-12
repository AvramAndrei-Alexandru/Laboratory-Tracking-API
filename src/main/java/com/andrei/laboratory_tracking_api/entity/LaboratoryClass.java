package com.andrei.laboratory_tracking_api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "laboratory_class")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryClass extends AbstractEntity{

    @NotNull
    @Column(name = "laboratory_number", unique = true)
    private int laboratoryNumber;

    @NotNull
    @Column(name = "laboratory_date")
    private Date laboratoryDate;

    @NotNull
    @Column(name = "curricula")
    private String curricula;

    @NotNull
    @Column(name = "description", length = 5000)
    private String description;

}
