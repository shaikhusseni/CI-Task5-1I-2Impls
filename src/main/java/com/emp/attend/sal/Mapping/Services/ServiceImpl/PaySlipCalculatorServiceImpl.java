package com.emp.attend.sal.Mapping.Services.ServiceImpl;


import com.emp.attend.sal.Mapping.EceptionHandler.InspireNetExceoptionHnadler;
import com.emp.attend.sal.Mapping.Services.PaySlipCalculatorService;
import com.emp.attend.sal.Mapping.Services.PaySlipService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@NoArgsConstructor

@AllArgsConstructor
@Service
public class PaySlipCalculatorServiceImpl implements PaySlipCalculatorService {


    Logger errorMessge= LoggerFactory.getLogger(PaySlipCalculatorServiceImpl.class);

    private PaySlipServiceImpl_DaysSlip dailyPaySlipCalculator;

    private PaySlipServiceImpl_MonthlySlip monthlyPaySlipCalculator;

    @Override
    public String calculatepaySlip(Long empId, String type) throws InspireNetExceoptionHnadler {
        PaySlipService paySlipCalculator = slipType(type);
        return paySlipCalculator.calculateSalSlip(empId);
    }


    /**
     * Initializes and returns the appropriate PaySlipService implementation based on the specified payslip type.
     *
     * @param type The type of payslip to generate (must be "daily" or "monthly")
     * @return The corresponding PaySlipService object
     * @throws IllegalArgumentException if an invalid payslip type is provided
     */
    private PaySlipService slipType(String type) {
        if (type.equalsIgnoreCase("daily")) {

            errorMessge.info("if condititon in slipServiceCalcuktion run sucessfully");
            return dailyPaySlipCalculator;
        } else if (type.equalsIgnoreCase("monthly")) {
            errorMessge.info("if condititon in slipServiceCalcuktion run not ");

            return monthlyPaySlipCalculator;
        } else {
            throw new IllegalArgumentException("Invalid payslip type: " + type);
        }

    }

}


//    private final PaySlipServiceImpl_MonthlySlip monthlySlip;
//@Autowired
//    private final PaySlipServiceImpl_DaysSlip daysSlip;

//    public PaySlipCalculatorServiceImpl() {
//        this.monthlySlip = new PaySlipServiceImpl_MonthlySlip();
//        this.daysSlip = new PaySlipServiceImpl_DaysSlip();
//    }

//    @Override
//    public String calculatepaySlip(Long empId, String type) {
//        try {
//            PaySlipService paySlipService = type.equalsIgnoreCase("days") ? daysSlip : monthlySlip;
//            return paySlipService.calculateSalSlip(empId);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to generate payslip", e);
//        }
//    }


//    private  PaySlipServiceImpl_DaysSlip dailyPaySlipCalculator ;
//    private  PaySlipServiceImpl_MonthlySlip monthlyPaySlipCalculator;
//
//    @Override
//    public String calculatepaySlip(Long empId, String type) {
//        try {
//            PaySlipCalculatorService paySlipCalculator = initImple(empId,type);
//            return paySlipCalculator.calculatepaySlip(empId,type);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to generate payslip", e);
//        }
//    }
//
//    private PaySlipCalculatorService initImple(Long empId, String type) {
//        if (type.equalsIgnoreCase("daily")) {
//            return    dailyPaySlipCalculator;
//        } else if (type.equalsIgnoreCase("monthly")) {
//            return   new PaySlipServiceImpl_MonthlySlip;
//        } else {
//            throw new IllegalArgumentException("Invalid payslip type: " + type);
//        }




