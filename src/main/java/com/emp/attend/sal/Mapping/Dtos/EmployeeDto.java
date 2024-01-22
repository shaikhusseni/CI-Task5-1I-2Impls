package com.emp.attend.sal.Mapping.Dtos;


import com.emp.attend.sal.Mapping.Entitys.Attendance;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private Long empId;

    private String empName;

    private String empEmail;

    private String empAddress;

    private String empPhno;

    private List<Attendance> attendanceRecords;


}
