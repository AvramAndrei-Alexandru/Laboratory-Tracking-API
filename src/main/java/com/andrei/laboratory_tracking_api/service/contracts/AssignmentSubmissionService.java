package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;

import java.util.List;

public interface AssignmentSubmissionService extends AbstractBaseService<AssignmentSubmission, Integer> {
    AssignmentSubmission save(AssignmentSubmission assignmentSubmission);
    AssignmentSubmission findByAssignmentIdAndStudentId(int assignmentId, int studentId);
    List<AssignmentSubmission> findAllSubmittedByCurrentUser(String email);
    List<AssignmentSubmission> findAllByAssignmentId(int assignmentId);
}
