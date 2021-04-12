package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import com.andrei.laboratory_tracking_api.entity.Grade;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class GradeDTO {

    private int id;

    @NotNull
    @JsonProperty(required = true)
    private int assignmentSubmissionId;

    @NotNull
    @JsonProperty(required = true)
    @Min(1)
    @Max(10)
    private int grade;

    public static GradeDTO fromEntityToDTO(Grade grade){
        return GradeDTO.builder()
                .id(grade.getId())
                .assignmentSubmissionId(grade.getAssignmentSubmission().getId())
                .grade(grade.getGrade())
                .build();
    }

    public static Grade fromDTOToGrade(GradeDTO gradeDTO) {
        return Grade.builder()
                .assignmentSubmission(AssignmentSubmission.builder().id(gradeDTO.getAssignmentSubmissionId()).build())
                .grade(gradeDTO.getGrade())
                .build();
    }

}
