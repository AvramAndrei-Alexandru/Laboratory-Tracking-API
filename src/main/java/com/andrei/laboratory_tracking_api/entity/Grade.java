package com.andrei.laboratory_tracking_api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "grade")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Grade extends AbstractEntity{

    @JoinColumn(name = "assignment_submission_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private AssignmentSubmission assignmentSubmission;

    @NotNull
    @Max(10)
    @Min(1)
    private int grade;
}
