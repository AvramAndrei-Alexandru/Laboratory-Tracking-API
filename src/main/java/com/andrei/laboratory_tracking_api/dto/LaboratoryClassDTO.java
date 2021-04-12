package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Builder
public class LaboratoryClassDTO {
    private int id;
    @JsonProperty(required = true)
    @NotNull
    private int laboratoryNumber;
    @JsonProperty(required = true)
    @NotNull
    private Date date;
    @JsonProperty(required = true)
    @NotNull
    private String curricula;
    @JsonProperty(required = true)
    @NotNull
    private String description;

    public static LaboratoryClassDTO getEntityToDTO(LaboratoryClass laboratoryClass) {
        return LaboratoryClassDTO.builder()
                .id(laboratoryClass.getId())
                .curricula(laboratoryClass.getCurricula())
                .laboratoryNumber(laboratoryClass.getLaboratoryNumber())
                .date(laboratoryClass.getLaboratoryDate())
                .description(laboratoryClass.getDescription())
                .build();
    }

    public static LaboratoryClass getDTOToEntity(LaboratoryClassDTO laboratoryClassDTO) {
        return LaboratoryClass.builder()
                .id(laboratoryClassDTO.getId())
                .curricula(laboratoryClassDTO.getCurricula())
                .laboratoryNumber(laboratoryClassDTO.getLaboratoryNumber())
                .laboratoryDate(laboratoryClassDTO.getDate())
                .description(laboratoryClassDTO.getDescription())
                .build();
    }
}
