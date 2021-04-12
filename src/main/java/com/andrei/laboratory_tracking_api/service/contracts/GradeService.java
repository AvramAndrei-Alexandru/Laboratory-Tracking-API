package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Grade;

public interface GradeService extends AbstractBaseService<Grade, Integer> {
    Grade save(Grade grade);
    Grade findBySubmittedAssignmentId(int id);
}
