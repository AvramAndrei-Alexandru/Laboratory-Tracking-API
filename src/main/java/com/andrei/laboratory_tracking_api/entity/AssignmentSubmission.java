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

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assignment_submission")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSubmission extends AbstractEntity {

    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Assignment assignment;

    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Student student;

    @NotNull
    private String link;

    private String comment;
}
