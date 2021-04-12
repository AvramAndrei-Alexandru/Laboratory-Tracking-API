package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends AbstractBaseRepository<Grade, Integer> {

    @Query(value = "select * from grade where assignment_submission_id=?", nativeQuery = true)
    Grade findBySubmittedAssignmentId(int id);
}
