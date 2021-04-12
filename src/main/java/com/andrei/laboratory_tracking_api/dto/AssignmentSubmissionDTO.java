package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.Assignment;
import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import com.andrei.laboratory_tracking_api.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AssignmentSubmissionDTO {

    private int id;

    @NotNull
    @JsonProperty(required = true)
    private int assignmentId;

    @NotNull
    @JsonProperty(required = true)
    private int studentId;

    @NotNull
    @JsonProperty(required = true)
    private String link;

    private String comment;


    public static AssignmentSubmissionDTO fromEntityToDTO(AssignmentSubmission assignmentSubmission) {
        return AssignmentSubmissionDTO.builder()
                .id(assignmentSubmission.getId())
                .assignmentId(assignmentSubmission.getAssignment().getId())
                .studentId(assignmentSubmission.getStudent().getId())
                .link(assignmentSubmission.getLink())
                .comment(assignmentSubmission.getComment())
                .build();
    }

    public static AssignmentSubmission fromDTOToEntity(AssignmentSubmissionDTO assignmentSubmissionDTO) {
        return AssignmentSubmission.builder()
                .comment(assignmentSubmissionDTO.getComment())
                .link(assignmentSubmissionDTO.getLink())
                .student(Student.builder().id(assignmentSubmissionDTO.getStudentId()).build())
                .assignment(Assignment.builder().id(assignmentSubmissionDTO.getAssignmentId()).build())
                .build();
    }
}
