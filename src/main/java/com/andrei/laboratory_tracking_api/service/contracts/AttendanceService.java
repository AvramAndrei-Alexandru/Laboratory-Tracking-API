package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Attendance;

import java.util.List;

public interface AttendanceService extends AbstractBaseService<Attendance, Integer> {
    Attendance save(Attendance attendance);
    Attendance update(int id, boolean attendance);
    Attendance getByStudentIdAndLaboratoryId(int studentId, int laboratoryId);
    List<Attendance> getAllByLaboratoryId(int id);
}
