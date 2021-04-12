package com.andrei.laboratory_tracking_api.dto;

import com.andrei.laboratory_tracking_api.service.model.UserStudent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;

@Data
@Builder
public class StudentIncomingDTO {

    @JsonProperty(required = true)
    @Email()
    private String email;
    @JsonProperty(required = true)
    private String password;
    @JsonProperty(required = true)
    private String fullName;
    @JsonProperty(required = true)
    private String token;
    @JsonProperty(required = true)
    private int groupID;
    @JsonProperty(required = true)
    private String hobby;

    public static UserStudent getDTOToUserStudent(StudentIncomingDTO studentIncomingDTO) {
        UserStudent userStudent = new UserStudent();
        userStudent.setEmail(studentIncomingDTO.getEmail());
        userStudent.setFullName(studentIncomingDTO.getFullName());
        userStudent.setGroupID(studentIncomingDTO.getGroupID());
        userStudent.setPassword(studentIncomingDTO.getPassword());
        userStudent.setToken(studentIncomingDTO.getToken());
        userStudent.setHobby(studentIncomingDTO.getHobby());
        return userStudent;
    }

}
