package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Student;
import com.andrei.laboratory_tracking_api.entity.StudentGroups;
import com.andrei.laboratory_tracking_api.entity.Tokens;
import com.andrei.laboratory_tracking_api.entity.User;
import com.andrei.laboratory_tracking_api.service.contracts.StudentFacade;
import com.andrei.laboratory_tracking_api.service.contracts.StudentService;
import com.andrei.laboratory_tracking_api.service.contracts.TokenService;
import com.andrei.laboratory_tracking_api.service.contracts.UserService;
import com.andrei.laboratory_tracking_api.service.model.UserStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentFacadeImpl implements StudentFacade {

    private final TokenService tokenService;
    private final UserService userService;
    private final StudentService studentService;

    public boolean createUserAndStudent(UserStudent userStudent) {

        User user = User.builder()
                .email(userStudent.getEmail())
                .password(userStudent.getPassword())
                .build();
        StudentGroups group = StudentGroups.builder()
                .id(userStudent.getGroupID())
                .build();
        Student student = Student.builder()
                .studentGroup(group)
                .fullName(userStudent.getFullName())
                .hobby(userStudent.getHobby())
                .build();
        student.setStudentGroup(group);
        student.setFullName(userStudent.getFullName());
        student.setHobby(userStudent.getHobby());
        Tokens tokens = tokenService.getByEmail(userStudent.getEmail());
        if(!userService.existsByEmail(userStudent.getEmail()) && studentService.canCreate(student) && tokens != null && tokens.getToken().equals(userStudent.getToken())) {
            User savedUser = userService.save(user);
            student.setUser(savedUser);
            studentService.save(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentById(int id) {
        if(studentService.findById(id).isEmpty()) {
            return false;
        }
        User user = studentService.findById(id).get().getUser();
        Tokens tokens = tokenService.getByEmail(user.getEmail());
        studentService.deleteById(id);
        tokenService.deleteById(tokens.getId());
        userService.deleteById(user.getId());
        return true;
    }
}
