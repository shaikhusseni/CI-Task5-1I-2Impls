package com.emp.attend.sal.Mapping.Services.ServiceImpl;

import com.emp.attend.sal.Mapping.Entitys.Attendance;
import com.emp.attend.sal.Mapping.Entitys.Employee;
import com.emp.attend.sal.Mapping.Entitys.PaySlip;
import com.emp.attend.sal.Mapping.Repositorys.AttendanceRepo;
import com.emp.attend.sal.Mapping.Repositorys.EmployeeRepo;
import com.emp.attend.sal.Mapping.Repositorys.PaySlipRepo;
import com.emp.attend.sal.Mapping.Services.PaySlipService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class PaySlipServiceImpl_MonthlySlip implements PaySlipService {


    @Autowired
    private PaySlipRepo salSlipCalculationRepo;

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private AttendanceRepo attendanceRepository;


    private static final int EMP_DAY_SAL = 500;
    private static final double EMPLOYEE_MONTHLY_SALARY = 30000;

    @Override
    public String calculateSalSlip(Long empId) {

        try {

            // Check if a paySlip already exists for the employee
            if (salSlipCalculationRepo.existsByEmployeeEmpId(empId)) {
                throw new RuntimeException("Payslip already exists for employee ID: " + empId);
            }

            // Fetch employee attendance records
            List<Attendance> attendanceList = attendanceRepository.findByEmployee_EmpId(empId);

            // checking employee Attendance Data is present in DB or not.
            if (attendanceList.isEmpty()) {
                throw new RuntimeException("No attendance records found for employee ID: " + empId);
            }

            // Calculate total present days for the month
            long totalPresentDays = attendanceList.stream()
                    .filter(attendance -> "Present".equalsIgnoreCase(attendance.getAttendStatus()))
                    .count();

            // Calculate total salary for the month
            double totalSalary = totalPresentDays * (EMPLOYEE_MONTHLY_SALARY / 30);

            // Fetch the employee
            Employee employee = employeeRepository.findById(empId)
                    .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));

            // Save the paySlip record

            PaySlip paySlip = new PaySlip();

            paySlip.setEmployee(employee);

            paySlip.setEmpDaysPresent((int) totalPresentDays);

            paySlip.setEmpDaySalary(EMPLOYEE_MONTHLY_SALARY / 30);

            paySlip.setEmpTotalSalary(totalSalary);

            paySlip.setCreatedAt(LocalDate.now());

            salSlipCalculationRepo.save(paySlip);


            return "Monthly Pay slip generated successfully for empId: " + empId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Determines the total number of days in the current month, ensuring accurate calculation
     * without relying on manual approximations.
     *
     * @return The number of days in the current month.  -> inste use roughly 30 days
     */
    private int getTotalDaysInMonth() {
        LocalDate currentDate = LocalDate.now();     // Obtains the current date
        YearMonth currentMonth = YearMonth.from(currentDate);  // Extracts the current month
        return currentMonth.lengthOfMonth();         // Returns the precise number of days in the month
    }

}
