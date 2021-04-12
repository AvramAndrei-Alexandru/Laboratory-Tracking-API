package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.andrei.laboratory_tracking_api.repository.LaboratoryClassRepository;
import com.andrei.laboratory_tracking_api.service.contracts.LaboratoryClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LaboratoryClassServiceImpl extends AbstractBaseServiceImpl<LaboratoryClass, Integer> implements LaboratoryClassService {

    private final LaboratoryClassRepository laboratoryClassRepository;

    public LaboratoryClassServiceImpl(LaboratoryClassRepository laboratoryClassRepository) {
        super(laboratoryClassRepository);
        this.laboratoryClassRepository = laboratoryClassRepository;
    }

    @Override
    public LaboratoryClass save(LaboratoryClass laboratoryClass) {
        if(getByLaboratoryNumber(laboratoryClass.getLaboratoryNumber()) == null) {
            return laboratoryClassRepository.save(laboratoryClass);
        }
        return null;
    }

    @Override
    public LaboratoryClass update(int id, LaboratoryClass laboratoryClass) {
        Optional<LaboratoryClass> savedLab = findById(id);
        if(savedLab.isEmpty()) {
            return null;
        }
        laboratoryClass.setId(savedLab.get().getId());
        LaboratoryClass existingLab = getByLaboratoryNumber(laboratoryClass.getId());
        if(existingLab != null && existingLab.getId() == laboratoryClass.getId()) {
            return laboratoryClassRepository.save(laboratoryClass);
        }
        if(existingLab == null) {
            return laboratoryClassRepository.save(laboratoryClass);
        }
        return null;
    }

    @Override
    public LaboratoryClass getByLaboratoryNumber(int laboratoryNumber) {
        return laboratoryClassRepository.getByLaboratoryNumber(laboratoryNumber);
    }
}
