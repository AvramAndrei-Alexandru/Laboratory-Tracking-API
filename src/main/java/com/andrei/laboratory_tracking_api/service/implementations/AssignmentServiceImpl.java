package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Assignment;
import com.andrei.laboratory_tracking_api.entity.LaboratoryClass;
import com.andrei.laboratory_tracking_api.repository.AssignmentRepository;
import com.andrei.laboratory_tracking_api.repository.LaboratoryClassRepository;
import com.andrei.laboratory_tracking_api.service.contracts.AssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AssignmentServiceImpl extends AbstractBaseServiceImpl<Assignment, Integer> implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final LaboratoryClassRepository laboratoryClassRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, LaboratoryClassRepository laboratoryClassRepository) {
        super(assignmentRepository);
        this.assignmentRepository = assignmentRepository;
        this.laboratoryClassRepository = laboratoryClassRepository;
    }

    @Override
    public Assignment save(Assignment assignment) {
        Optional<LaboratoryClass> labClass = laboratoryClassRepository.findById(assignment.getLaboratoryClass().getId());
        if(getByLaboratoryId(assignment.getLaboratoryClass().getId()) == null && labClass.isPresent()) {
            assignment.setLaboratoryClass(labClass.get());
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    @Override
    public Assignment update(int id, Assignment assignment) {
        Optional<Assignment> foundAssignment = findById(id);
        Optional<LaboratoryClass> labClass = laboratoryClassRepository.findById(assignment.getLaboratoryClass().getId());
        if(foundAssignment.isPresent() && foundAssignment.get().getLaboratoryClass().getId() == assignment.getLaboratoryClass().getId() && labClass.isPresent()) {
            assignment.setLaboratoryClass(labClass.get());
            assignment.setId(foundAssignment.get().getId());
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    @Override
    public Assignment getByLaboratoryId(int laboratoryId) {
        return assignmentRepository.getByLaboratoryId(laboratoryId);
    }
}
