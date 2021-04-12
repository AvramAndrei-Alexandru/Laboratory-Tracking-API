package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAndStudentAccess;
import com.andrei.laboratory_tracking_api.dto.AssignmentDTO;
import com.andrei.laboratory_tracking_api.entity.Assignment;
import com.andrei.laboratory_tracking_api.service.contracts.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
public class AssignmentRestController {

    private final AssignmentService assignmentService;

    @TeacherAndStudentAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        List<AssignmentDTO> assignmentDTOS = assignmentService.findAll().stream()
                .map(AssignmentDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(OK)
                .body(assignmentDTOS);
    }

    @TeacherAccess
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AssignmentDTO> createAssignment(@Valid @RequestBody AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentService.save(AssignmentDTO.fromDTOToEntity(assignmentDTO));
        if(assignment != null) {
            return ResponseEntity
                    .status(CREATED)
                    .body(AssignmentDTO.fromEntityToDTO(assignment));
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .build();
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable int id) {
        if(assignmentService.deleteById(id)) {
            return ResponseEntity
                    .status(OK)
                    .build();
        }
        return ResponseEntity
                .status(NOT_FOUND)
                .build();
    }

    @TeacherAccess
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AssignmentDTO> editAssignment(@PathVariable int id, @Valid @RequestBody AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentService.update(id, AssignmentDTO.fromDTOToEntity(assignmentDTO));
        if(assignment != null) {
            return ResponseEntity
                    .status(OK)
                    .body(AssignmentDTO.fromEntityToDTO(assignment));
        }
        return ResponseEntity
                .status(BAD_REQUEST)
                .build();
    }

    @TeacherAndStudentAccess
    @GetMapping(value = "/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentByLabId(@PathVariable int id) {
        Assignment assignment = assignmentService.getByLaboratoryId(id);
        if(assignment != null) {
            return ResponseEntity
                    .status(OK)
                    .body(AssignmentDTO.fromEntityToDTO(assignment));
        }
        return ResponseEntity
                .status(NOT_FOUND)
                .build();
    }
}
