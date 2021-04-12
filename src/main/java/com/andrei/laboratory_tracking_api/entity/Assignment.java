package com.andrei.laboratory_tracking_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assignment")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Assignment extends AbstractEntity{

    @NotNull
    private String name;

    @NotNull
    private Date deadline;

    @NotNull
    @Column(length = 1000)
    private String description;

    @JoinColumn(name = "laboratory_class_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private LaboratoryClass laboratoryClass;
}
