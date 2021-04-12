package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Attendance;
import com.andrei.laboratory_tracking_api.repository.AttendanceRepository;
import com.andrei.laboratory_tracking_api.service.contracts.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceServiceImpl extends AbstractBaseServiceImpl<Attendance, Integer> implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        super(attendanceRepository);
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance save(Attendance attendance) {
        if(getByStudentIdAndLaboratoryId(attendance.getStudent().getId(), attendance.getLaboratoryClass().getId()) == null) {
            return attendanceRepository.save(attendance);
        }
        return null;
    }

    @Override
    public Attendance update(int id, boolean attendance) {
        Optional<Attendance> found = findById(id);
        if(found.isPresent()) {
            found.get().setPresent(attendance);
            return found.get();
        }
        return null;
    }

    @Override
    public Attendance getByStudentIdAndLaboratoryId(int studentId, int laboratoryId) {
        return attendanceRepository.getByLaboratoryIdAndStudentId(laboratoryId, studentId);
    }

    @Override
    public List<Attendance> getAllByLaboratoryId(int id) {
        return attendanceRepository.getAllByLaboratoryId(id);
    }
}
