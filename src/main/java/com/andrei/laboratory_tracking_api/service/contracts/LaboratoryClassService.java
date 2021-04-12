package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;

import java.util.List;

public interface LaboratoryClassService extends AbstractBaseService<LaboratoryClass, Integer>{
    LaboratoryClass save(LaboratoryClass laboratoryClass);
    LaboratoryClass update(int id, LaboratoryClass laboratoryClass);
    LaboratoryClass getByLaboratoryNumber(int laboratoryNumber);
}
