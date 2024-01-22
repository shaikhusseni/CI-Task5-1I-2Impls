package com.emp.attend.sal.Mapping.Controllers;

import com.emp.attend.sal.Mapping.Dtos.AttendanceDto;
import com.emp.attend.sal.Mapping.Dtos.EmployeeDto;
import com.emp.attend.sal.Mapping.EceptionHandler.InspireNetExceoptionHnadler;
import com.emp.attend.sal.Mapping.Entitys.Employee;
import com.emp.attend.sal.Mapping.Services.AttendanceService;
import com.emp.attend.sal.Mapping.Services.EmployeeService;
import com.emp.attend.sal.Mapping.Services.PaySlipCalculatorService;
import com.emp.attend.sal.Mapping.Services.PaySlipService;
import com.emp.attend.sal.Mapping.Services.ServiceImpl.PaySlipCalculatorServiceImpl;
import com.emp.attend.sal.Mapping.Services.ServiceImpl.PaySlipServiceImpl_DaysSlip;
//import com.emp.attend.sal.Mapping.Services.ServiceImpl.PaySlipServiceImpl_MonthlySlip;
import com.emp.attend.sal.Mapping.Services.ServiceImpl.PaySlipServiceImpl_MonthlySlip;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

//@Slf4j
@Data
@RestController
@RequestMapping("/employeeapi")
public class EmployeeController {

    @Autowired
    private PaySlipServiceImpl_DaysSlip daysSlip;


    @Autowired
    private PaySlipServiceImpl_MonthlySlip monthlySlip;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;


    // This mapping is for  saving Employee along with Attendance
    @PostMapping("/save")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {


        return attendanceService.saveEmployee(employeeDto);
    }


    //    This mapping is  for adding Attendance For an Employee exists if his empId is Exists in DB Of Attendance
    @PostMapping("/{empId}/addattendance")
    public ResponseEntity<?> addAttendance(@PathVariable Long empId, @RequestBody AttendanceDto attendanceDto) {
        try {
            attendanceService.addAttendance(empId, attendanceDto);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            // Handle other potential errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @Autowired
    private final PaySlipCalculatorService paySlipCalculatorService;

    public EmployeeController(PaySlipCalculatorServiceImpl paySlipCalculatorService) {
        this.paySlipCalculatorService = paySlipCalculatorService;
    }

    @GetMapping("/generate/{empId}/{type}")
    public ResponseEntity<String> generatePaySlip(@PathVariable Long empId, @PathVariable String type) throws InspireNetExceoptionHnadler {

        try {
            String result = paySlipCalculatorService.calculatepaySlip(empId, type);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle exceptions gracefully, providing appropriate error messages
            return ResponseEntity.badRequest().body("Failed to generate SalSlip" + e.getMessage());
        }

    }
}


//    This is for generating the paySlip for an Employee based on Employee empId
//    @GetMapping("/generate/{empId}")
//    public ResponseEntity<String> paySlipDays(@PathVariable Long empId)
//    {
//
//        String s = daysSlip.calculateSalSlip(empId);
//
////        errorMessage.info("Monthly salip generation done sucessfuly done"+empId);
//
//
//        return ResponseEntity.ok("SalSlip generated");
//
//    }

//    @GetMapping("/generate/{empId}/{type}")
//    public ResponseEntity<String> generatePaySlip(@PathVariable Long empId, @PathVariable String type) {
//
//        PaySlipService paySlipService = type.equalsIgnoreCase("days") ? daysSlip : monthlySlip;
//        String result = paySlipService.calculateSalSlip(empId);
//
//        return ResponseEntity.ok("PaySlip generated successfully for employee " + empId);
//    }



