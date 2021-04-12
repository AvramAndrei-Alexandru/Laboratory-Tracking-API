package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.Attendance;
import com.andrei.laboratory_tracking_api.service.model.AttendanceServiceModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AttendanceDTO {

    private int id;
    @NotNull
    @JsonProperty(required = true)
    private int laboratoryId;
    @NotNull
    @JsonProperty(required = true)
    private int studentId;
    @NotNull
    @JsonProperty(required = true)
    private boolean present;

    public static AttendanceDTO fromEntityToDTO(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .laboratoryId(attendance.getLaboratoryClass().getId())
                .studentId(attendance.getStudent().getId())
                .present(attendance.isPresent())
                .build();
    }

    public static AttendanceServiceModel fromDTOToServiceModel(AttendanceDTO dto) {
        return AttendanceServiceModel.builder()
                .laboratoryId(dto.getLaboratoryId())
                .studentId(dto.getStudentId())
                .present(dto.isPresent())
                .build();
    }
}
