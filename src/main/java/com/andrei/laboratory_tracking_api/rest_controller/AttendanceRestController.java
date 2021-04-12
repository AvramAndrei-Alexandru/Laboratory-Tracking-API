package com.andrei.laboratory_tracking_api.rest_controller;

import com.andrei.laboratory_tracking_api.config.TeacherAccess;
import com.andrei.laboratory_tracking_api.dto.AttendanceDTO;
import com.andrei.laboratory_tracking_api.entity.Attendance;
import com.andrei.laboratory_tracking_api.service.contracts.AttendanceFacade;
import com.andrei.laboratory_tracking_api.service.contracts.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/attendance")
@RequiredArgsConstructor
public class AttendanceRestController {

    private final AttendanceFacade attendanceFacade;
    private final AttendanceService attendanceService;

    @TeacherAccess
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> attendanceDTOS = attendanceService.findAll().stream()
                .map(AttendanceDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(attendanceDTOS);
    }

    @TeacherAccess
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AttendanceDTO> createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceFacade.createAttendance(AttendanceDTO.fromDTOToServiceModel(attendanceDTO));
        if(attendance != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(AttendanceDTO.fromEntityToDTO(attendance));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @TeacherAccess
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable int id) {
        if(attendanceService.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @TeacherAccess
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AttendanceDTO> editAttendance(@PathVariable int id, @RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = attendanceService.update(id, attendanceDTO.isPresent());
        if(attendance != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(AttendanceDTO.fromEntityToDTO(attendance));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @TeacherAccess
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AttendanceDTO>> getAllByLaboratoryId(@PathVariable int id) {
        List<AttendanceDTO> dtos = attendanceService.getAllByLaboratoryId(id).stream()
                .map(AttendanceDTO::fromEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dtos);
    }
}
