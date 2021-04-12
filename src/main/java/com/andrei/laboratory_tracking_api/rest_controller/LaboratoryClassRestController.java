package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.config.TeacherAndStudentAccess;
import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.andrei.laboratory_tracking_api.dto.LaboratoryClassDTO;
import com.andrei.laboratory_tracking_api.service.contracts.LaboratoryClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/laboratory-class")
@RequiredArgsConstructor
public class LaboratoryClassRestController {

    private final LaboratoryClassService laboratoryClassService;

    @TeacherAndStudentAccess
    @GetMapping
    public ResponseEntity<List<LaboratoryClassDTO>> getAllLaboratories() {
        List<LaboratoryClassDTO> classOutDTOS = laboratoryClassService.findAll().stream()
                .map(LaboratoryClassDTO::getEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(classOutDTOS);
    }

    @TeacherAccess
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LaboratoryClassDTO> createLaboratoryClass(@Valid @RequestBody LaboratoryClassDTO laboratoryClassDTO) {
        LaboratoryClass savedClass = laboratoryClassService.save(LaboratoryClassDTO.getDTOToEntity(laboratoryClassDTO));
        if(savedClass != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(LaboratoryClassDTO.getEntityToDTO(savedClass));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @TeacherAccess
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LaboratoryClassDTO> editLaboratory(@PathVariable int id, @Valid @RequestBody LaboratoryClassDTO laboratoryClassDTO) {
        LaboratoryClass editedLab = laboratoryClassService.update(id, LaboratoryClassDTO.getDTOToEntity(laboratoryClassDTO));
        if(editedLab != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(LaboratoryClassDTO.getEntityToDTO(editedLab));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable int id) {
        if(laboratoryClassService.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
