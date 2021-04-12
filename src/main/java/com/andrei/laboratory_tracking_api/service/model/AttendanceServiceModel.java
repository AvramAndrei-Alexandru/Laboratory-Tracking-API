package com.andrei.laboratory_tracking_api.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceServiceModel {
    private int laboratoryId;
    private int studentId;
    private boolean present;
}
