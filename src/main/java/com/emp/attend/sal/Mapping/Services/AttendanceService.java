package com.emp.attend.sal.Mapping.Services;

import com.emp.attend.sal.Mapping.Dtos.AttendanceDto;
import com.emp.attend.sal.Mapping.Dtos.EmployeeDto;

public interface AttendanceService {

    /**
     * Saves the provided employee information to the database.
     *
     * @param employeeDto The DTO (Data Transfer Object) containing the employee information to be saved.
     * @return A DTO representing the saved employee.
     */
    EmployeeDto saveEmployee(EmployeeDto employeeDto);


    /**
     * Adds attendance information for an employee with the specified employeeId.
     *
     * @param employeeId    The ID of the employee for whom attendance is being added.
     * @param attendanceDto The DTO containing the attendance information to be added.
     * @return A DTO representing the added attendance information.
     */
    AttendanceDto addAttendance(Long employeeId, AttendanceDto attendanceDto);


}
