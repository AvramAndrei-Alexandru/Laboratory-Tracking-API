package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Student;

public interface StudentService extends AbstractBaseService<Student, Integer>{
     void save(Student student);
     boolean canCreate(Student student);
     boolean editHobby(int id, String newHobby);
     void update(Student student);
     Student findByUserId(int userId);
}
