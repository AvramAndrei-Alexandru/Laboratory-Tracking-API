package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Assignment;
import com.andrei.laboratory_tracking_api.entity.AssignmentSubmission;
import com.andrei.laboratory_tracking_api.entity.Student;
import com.andrei.laboratory_tracking_api.repository.AssignmentRepository;
import com.andrei.laboratory_tracking_api.repository.AssignmentSubmissionRepository;
import com.andrei.laboratory_tracking_api.repository.StudentRepository;
import com.andrei.laboratory_tracking_api.repository.UserRepository;
import com.andrei.laboratory_tracking_api.service.contracts.AssignmentSubmissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssignmentSubmissionServiceImpl extends AbstractBaseServiceImpl<AssignmentSubmission, Integer> implements AssignmentSubmissionService {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public AssignmentSubmissionServiceImpl(AssignmentSubmissionRepository assignmentSubmissionRepository, AssignmentRepository assignmentRepository, StudentRepository studentRepository, UserRepository userRepository) {
        super(assignmentSubmissionRepository);
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }


    @Override
    public AssignmentSubmission save(AssignmentSubmission assignmentSubmission) {
        Optional<Student> foundStudent = studentRepository.findById(assignmentSubmission.getStudent().getId());
        Optional<Assignment> foundAssignment = assignmentRepository.findById(assignmentSubmission.getAssignment().getId());
        AssignmentSubmission foundAssignmentSubmission = findByAssignmentIdAndStudentId(assignmentSubmission.getAssignment().getId(), assignmentSubmission.getStudent().getId());
        if(foundAssignmentSubmission == null && foundStudent.isPresent() && foundAssignment.isPresent()) {
            assignmentSubmission.setAssignment(foundAssignment.get());
            assignmentSubmission.setStudent(foundStudent.get());
            return assignmentSubmissionRepository.save(assignmentSubmission);
        }
        return null;
    }

    @Override
    public AssignmentSubmission findByAssignmentIdAndStudentId(int assignmentId, int studentId) {
        return assignmentSubmissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    @Override
    public List<AssignmentSubmission> findAllSubmittedByCurrentUser(String email) {
        return assignmentSubmissionRepository.findAllByStudentId(studentRepository.findByUserId(userRepository.findByEmail(email).getId()).getId());
    }

    @Override
    public List<AssignmentSubmission> findAllByAssignmentId(int assignmentId) {
        return assignmentSubmissionRepository.findAllByAssignmentId(assignmentId);
    }


}
