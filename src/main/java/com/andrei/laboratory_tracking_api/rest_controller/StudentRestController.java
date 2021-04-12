package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAndStudentAccess;
import com.andrei.laboratory_tracking_api.dto.StudentIncomingDTO;
import com.andrei.laboratory_tracking_api.dto.StudentOutDTO;
import com.andrei.laboratory_tracking_api.service.contracts.StudentFacade;
import com.andrei.laboratory_tracking_api.service.contracts.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/student")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentFacade studentFacade;
    private final StudentService studentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentIncomingDTO studentIncomingDTO) {
        if(studentFacade.createUserAndStudent(StudentIncomingDTO.getDTOToUserStudent(studentIncomingDTO))) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @TeacherAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StudentOutDTO>> getAllStudents() {
        List<StudentOutDTO> studentsDTO = studentService.findAll().stream()
                .map(StudentOutDTO::getEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(studentsDTO);
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if(studentFacade.deleteStudentById(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @TeacherAndStudentAccess
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> editStudentHobby(@PathVariable int id, @RequestBody StudentIncomingDTO studentIncomingDTO) {
        if(studentIncomingDTO.getHobby() != null && studentService.editHobby(id, studentIncomingDTO.getHobby())) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
