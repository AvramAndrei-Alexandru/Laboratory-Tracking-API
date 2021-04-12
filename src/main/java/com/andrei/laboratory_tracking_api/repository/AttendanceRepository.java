package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends AbstractBaseRepository<Attendance, Integer> {

    @Query(value = "select * from attendance where laboratory_class_id=? and student_id=?", nativeQuery = true)
    Attendance getByLaboratoryIdAndStudentId(int laboratoryId, int studentId);

    @Query(value = "select * from attendance where laboratory_class_id=?", nativeQuery = true)
    List<Attendance> getAllByLaboratoryId(int id);

}
