package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.Assignment;
import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Builder
@Data
public class AssignmentDTO {

    private int id;
    @NotNull
    @JsonProperty(required = true)
    private String name;
    @NotNull
    @JsonProperty(required = true)
    private Date deadline;
    @NotNull
    @JsonProperty(required = true)
    private String description;
    @NotNull
    @JsonProperty(required = true)
    private int laboratoryClassId;

   public static AssignmentDTO fromEntityToDTO(Assignment assignment) {
       return AssignmentDTO.builder()
               .id(assignment.getId())
               .name(assignment.getName())
               .deadline(assignment.getDeadline())
               .description(assignment.getDescription())
               .laboratoryClassId(assignment.getLaboratoryClass().getId())
               .build();
   }

   public static Assignment fromDTOToEntity(AssignmentDTO assignmentDTO) {
       return Assignment.builder()
               .name(assignmentDTO.getName())
               .deadline(assignmentDTO.getDeadline())
               .description(assignmentDTO.getDescription())
               .laboratoryClass(LaboratoryClass.builder().id(assignmentDTO.getLaboratoryClassId()).build())
               .build();
   }
}
