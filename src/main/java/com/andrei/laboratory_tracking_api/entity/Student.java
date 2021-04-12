package com.andrei.laboratory_tracking_api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AbstractEntity {
    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;


    @JoinColumn(name = "student_group_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private StudentGroups studentGroup;

    @Column(name = "hobby", length = 1000)
    private String hobby;



}
