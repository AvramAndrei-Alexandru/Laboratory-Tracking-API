package com.andrei.laboratory_tracking_api.service.implementations;

import com.andrei.laboratory_tracking_api.entity.Student;
import com.andrei.laboratory_tracking_api.entity.StudentGroups;
import com.andrei.laboratory_tracking_api.repository.StudentGroupsRepository;
import com.andrei.laboratory_tracking_api.repository.StudentRepository;
import com.andrei.laboratory_tracking_api.service.contracts.AbstractBaseService;
import com.andrei.laboratory_tracking_api.service.contracts.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl extends AbstractBaseServiceImpl<Student, Integer> implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentGroupsRepository studentGroupsRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentGroupsRepository studentGroupsRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.studentGroupsRepository = studentGroupsRepository;
    }

    @Override
    public void save(Student student) {
        StudentGroups group = studentGroupsRepository.getOne(student.getStudentGroup().getId());
        if(group != null) {
            student.setStudentGroup(group);
            studentRepository.save(student);
        }
    }

    @Override
    public boolean canCreate(Student student) {
        Optional<StudentGroups> group = studentGroupsRepository.findById(student.getStudentGroup().getId());
        return group.isPresent();
    }

    @Override
    public boolean editHobby(int id, String newHobby) {
        Optional<Student> student = findById(id);
        if(student.isPresent()) {
            student.get().setHobby(newHobby);
            update(student.get());
            return true;
        }
        return false;
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findByUserId(int userId) {
        return studentRepository.findByUserId(userId);
    }
}
