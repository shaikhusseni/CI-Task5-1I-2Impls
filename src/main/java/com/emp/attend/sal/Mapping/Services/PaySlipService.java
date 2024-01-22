package com.emp.attend.sal.Mapping.Services;

import com.emp.attend.sal.Mapping.EceptionHandler.InspireNetExceoptionHnadler;

public interface PaySlipService {

    /**
     * Calculates the salary slip for the employee with the specified empId.*
     *
     * @param empId The ID of the employee for whom the salary slip is to be calculated.
     * @return A string representation of the generated salary slip.
     */

    String calculateSalSlip(Long empId) throws InspireNetExceoptionHnadler;
}
