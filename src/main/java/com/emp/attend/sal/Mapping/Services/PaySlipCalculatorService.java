package com.emp.attend.sal.Mapping.Services;

import com.emp.attend.sal.Mapping.EceptionHandler.InspireNetExceoptionHnadler;

public interface PaySlipCalculatorService {

    /**
     * Calculates and generates a payslip for the specified employee, based on the provided type.
     *
     * @param empId The ID of the employee for whom to generate the payslip.
     * @param type  The type of payslip to generate, either "monthly" or "days".
     * @return A message indicating the success or failure of payslip generation, along with additional details.
     * @throws Exception If an error occurs during payslip generation.
     */
    public String calculatepaySlip(Long empId, String type) throws InspireNetExceoptionHnadler;

}
