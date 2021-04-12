package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.StudentAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAndStudentAccess;
import com.andrei.laboratory_tracking_api.dto.AssignmentSubmissionDTO;
import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import com.andrei.laboratory_tracking_api.service.contracts.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignment-submission")
@RequiredArgsConstructor
public class AssignmentSubmissionRestController {

    private final AssignmentSubmissionService assignmentSubmissionService;

    @TeacherAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssignmentSubmissionDTO>> getAllSubmittedAssignments() {
        List<AssignmentSubmissionDTO> assignmentSubmissionDTOS = assignmentSubmissionService.findAll().stream()
                .map(AssignmentSubmissionDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assignmentSubmissionDTOS);
    }

    @TeacherAndStudentAccess
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AssignmentSubmissionDTO> submitAssignment(@Valid @RequestBody AssignmentSubmissionDTO assignmentSubmissionDTO) {
        AssignmentSubmission assignmentSubmission = assignmentSubmissionService.save(AssignmentSubmissionDTO.fromDTOToEntity(assignmentSubmissionDTO));
        if(assignmentSubmission != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(AssignmentSubmissionDTO.fromEntityToDTO(assignmentSubmission));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @StudentAccess
    @GetMapping(value = "/student", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssignmentSubmissionDTO>> getAllAssignmentsSubmittedByUser() {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_STUDENT]")) {
            List<AssignmentSubmissionDTO> assignmentSubmissionDTOS =
                    assignmentSubmissionService.findAllSubmittedByCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName()).stream()
                    .map(AssignmentSubmissionDTO::fromEntityToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentSubmissionDTOS);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @TeacherAccess
    @GetMapping(value = "/assignment/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssignmentSubmissionDTO>> getAllSubmittedAssignmentsForAssignmentId(@PathVariable int id) {
        List<AssignmentSubmissionDTO> assignmentSubmissionDTOS = assignmentSubmissionService.findAllByAssignmentId(id).stream()
                .map(AssignmentSubmissionDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assignmentSubmissionDTOS);
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeSubmission(@PathVariable int id) {
        if(assignmentSubmissionService.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
