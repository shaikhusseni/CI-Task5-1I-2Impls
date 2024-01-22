package com.emp.attend.sal.Mapping.Repositorys;


import com.emp.attend.sal.Mapping.Entitys.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaySlipRepo extends JpaRepository<PaySlip, Long> {

    /**
     * Checks if an employee with the specified empId exists in the database.
     *
     * @param empId The empId of the employee to check.
     * @return true if an employee with the given empId exists, false otherwise.
     */
    boolean existsByEmployeeEmpId(Long empId);

}
