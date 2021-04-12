package com.andrei.laboratory_tracking_api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student_groups")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroups extends AbstractEntity{
    @NotNull
    @Column(name = "group_name", unique = true)
    private String groupName;
}
