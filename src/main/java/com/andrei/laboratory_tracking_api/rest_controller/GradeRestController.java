package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.dto.GradeDTO;
import com.andrei.laboratory_tracking_api.entity.Grade;
import com.andrei.laboratory_tracking_api.service.contracts.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeRestController {

    private final GradeService gradeService;

    @TeacherAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        List<GradeDTO> gradeDTOS = gradeService.findAll().stream()
                .map(GradeDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gradeDTOS);
    }

    @TeacherAccess
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GradeDTO> gradeAssignment(@Valid @RequestBody GradeDTO gradeDTO) {
        Grade savedGrade = gradeService.save(GradeDTO.fromDTOToGrade(gradeDTO));
        if(savedGrade != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(GradeDTO.fromEntityToDTO(savedGrade));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable int id){
        if(gradeService.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
