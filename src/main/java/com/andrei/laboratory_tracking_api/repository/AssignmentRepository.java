package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.Assignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends AbstractBaseRepository<Assignment, Integer> {

    @Query(value = "select * from assignment where laboratory_class_id=?", nativeQuery = true)
    Assignment getByLaboratoryId(int laboratoryId);
}
