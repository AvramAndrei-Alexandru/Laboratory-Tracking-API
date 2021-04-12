package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Attendance;
import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.andrei.laboratory_tracking_api.entity.Student;
import com.andrei.laboratory_tracking_api.entity.User;
import com.andrei.laboratory_tracking_api.service.contracts.*;
import com.andrei.laboratory_tracking_api.service.model.AttendanceServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceFacadeImpl implements AttendanceFacade {

    private final LaboratoryClassService laboratoryClassService;
    private final StudentService studentService;
    private final AttendanceService attendanceService;

    @Override
    public Attendance createAttendance(AttendanceServiceModel attendanceServiceModel) {
        Optional<Student> student = studentService.findById(attendanceServiceModel.getStudentId());
        Optional<LaboratoryClass> laboratoryClass = laboratoryClassService.findById(attendanceServiceModel.getLaboratoryId());
        if(student.isPresent() && laboratoryClass.isPresent()) {
            Attendance attendance = Attendance.builder()
                    .laboratoryClass(laboratoryClass.get())
                    .student(student.get())
                    .present(attendanceServiceModel.isPresent())
                    .build();
            return attendanceService.save(attendance);
        }
        return null;
    }
}
