package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Assignment;

public interface AssignmentService extends AbstractBaseService<Assignment, Integer> {
    Assignment save(Assignment assignment);
    Assignment update(int id, Assignment assignment);
    Assignment getByLaboratoryId(int laboratoryId);
}
