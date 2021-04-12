package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import com.andrei.laboratory_tracking_api.entity.Grade;
import com.andrei.laboratory_tracking_api.repository.AssignmentSubmissionRepository;
import com.andrei.laboratory_tracking_api.repository.GradeRepository;
import com.andrei.laboratory_tracking_api.service.contracts.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GradeServiceImpl extends AbstractBaseServiceImpl<Grade, Integer> implements GradeService {

    private final GradeRepository gradeRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, AssignmentSubmissionRepository assignmentSubmissionRepository) {
        super(gradeRepository);
        this.gradeRepository = gradeRepository;
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
    }

    @Override
    public Grade save(Grade grade) {
        Optional<AssignmentSubmission> submission = assignmentSubmissionRepository.findById(grade.getAssignmentSubmission().getId());
        if(submission.isPresent() && findBySubmittedAssignmentId(grade.getAssignmentSubmission().getId()) == null) {
            grade.setAssignmentSubmission(submission.get());
            return gradeRepository.save(grade);
        }
        return null;
    }

    @Override
    public Grade findBySubmittedAssignmentId(int id) {
        return gradeRepository.findBySubmittedAssignmentId(id);
    }
}
