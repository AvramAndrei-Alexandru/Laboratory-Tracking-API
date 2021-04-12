package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractBaseRepository<Student, Integer> {

    @Query(value = "select * from student where user_id=?", nativeQuery = true)
    Student findByUserId(int userId);
}
