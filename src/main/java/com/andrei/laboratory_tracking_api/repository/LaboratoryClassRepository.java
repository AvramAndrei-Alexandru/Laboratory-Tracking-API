package com.andrei.laboratory_tracking_api.repository;

import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryClassRepository extends AbstractBaseRepository<LaboratoryClass, Integer> {
    LaboratoryClass getByLaboratoryNumber(int laboratoryNumber);
}
