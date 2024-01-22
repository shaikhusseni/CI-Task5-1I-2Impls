package com.emp.attend.sal.Mapping.Services.ServiceImpl;

import com.emp.attend.sal.Mapping.EceptionHandler.ApiErrorCodes;
import com.emp.attend.sal.Mapping.EceptionHandler.InspireNetExceoptionHnadler;
import com.emp.attend.sal.Mapping.Entitys.Attendance;
import com.emp.attend.sal.Mapping.Entitys.Employee;
import com.emp.attend.sal.Mapping.Entitys.PaySlip;
import com.emp.attend.sal.Mapping.Repositorys.AttendanceRepo;
import com.emp.attend.sal.Mapping.Repositorys.EmployeeRepo;
import com.emp.attend.sal.Mapping.Repositorys.PaySlipRepo;
import com.emp.attend.sal.Mapping.Services.AttendanceService;
import com.emp.attend.sal.Mapping.Services.PaySlipService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class PaySlipServiceImpl_DaysSlip implements PaySlipService {

    private static final double EMPLOYEE_PERDAY_SAL = 100;

    @Autowired
    private PaySlipRepo paySlipRepo;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public String calculateSalSlip(Long empId) throws InspireNetExceoptionHnadler {


        try {

            //Checking paySlip is
            if (paySlipRepo.existsByEmployeeEmpId(empId)) {
                throw new RuntimeException("EmpId is Already is Existed");
            }

//        DOut for me  *****************************************************
//        List<Attendance> attendanceList=attendanceRepo.findById(empId);
            List<Attendance> attendanceList = attendanceRepo.findByEmployee_EmpId(empId);

//checking employee attendance data is present or not
            if (attendanceList.isEmpty()) {
                throw new InspireNetExceoptionHnadler(ApiErrorCodes.NO_ATTENDANCE_RECORDS);
            }

            long totalPresentDays = 0;

            // Calculate total present days for the month
            totalPresentDays = attendanceList.stream()
                    .filter(attendance -> "Present".equalsIgnoreCase(attendance.getAttendStatus()))
                    .count();
            double totalSalary = totalPresentDays * EMPLOYEE_PERDAY_SAL;

            // Fetch the employee
            Employee employee = employeeRepo.findById(empId)
                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));


//        Pay Slip Object-> for setting the payslip  data into DB as an record
            PaySlip paySlip = new PaySlip();

            paySlip.setEmployee(employee);

            paySlip.setCreatedAt(LocalDate.now());

            paySlip.setEmpDaySalary(EMPLOYEE_PERDAY_SAL);

            paySlip.setEmpTotalSalary(totalSalary);

            paySlipRepo.save(paySlip);

            return "Days Pay slip generated successfully for empId: " + empId;

        } catch (Exception e) {
            throw new RuntimeException("Pay Slip not generated");


    }}
}