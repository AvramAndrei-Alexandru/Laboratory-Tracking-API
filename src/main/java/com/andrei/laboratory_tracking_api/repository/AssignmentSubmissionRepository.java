package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentSubmissionRepository extends AbstractBaseRepository<AssignmentSubmission, Integer> {

    @Query(value = "select * from assignment_submission where assignment_id=? and student_id=?", nativeQuery = true)
    AssignmentSubmission findByAssignmentIdAndStudentId(int assignmentId, int studentId);

    @Query(value = "select * from assignment_submission where student_id=?", nativeQuery = true)
    List<AssignmentSubmission> findAllByStudentId(int studentId);

    @Query(value = "select * from assignment_submission where assignment_id=?", nativeQuery = true)
    List<AssignmentSubmission> findAllByAssignmentId(int assignmentId);
}
