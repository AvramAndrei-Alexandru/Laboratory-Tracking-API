package com.andrei.laboratory_tracking_api.service.contracts;

import com.andrei.laboratory_tracking_api.entity.Attendance;
import com.andrei.laboratory_tracking_api.service.model.AttendanceServiceModel;

public interface AttendanceFacade {
    Attendance createAttendance(AttendanceServiceModel attendanceServiceModel);
}
