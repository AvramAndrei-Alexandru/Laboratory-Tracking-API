package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.entity.Student;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentOutDTO {

    private int id;
    private String email;
    private String fullName;
    private int groupID;
    private String groupNumber;
    private String hobby;

    public static StudentOutDTO getEntityToDTO(Student student) {
        return StudentOutDTO.builder()
                .email(student.getUser().getEmail())
                .fullName(student.getFullName())
                .groupID(student.getStudentGroup().getId())
                .groupNumber(student.getStudentGroup().getGroupName())
                .id(student.getId())
                .hobby(student.getHobby())
                .build();
    }
}
