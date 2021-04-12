package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.service.model.UserStudent;

public interface StudentFacade {
     boolean createUserAndStudent(UserStudent userStudent);
     boolean deleteStudentById(int id);
}
