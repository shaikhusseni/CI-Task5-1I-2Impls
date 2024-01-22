package com.emp.attend.sal.Mapping.Entitys;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "PAYSLIP")
public class PaySlip {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PYS_ID")
    private Long paySlipId;

    @Column(name = "PYS_DAYS")
    private int empDaysPresent;

    @Column(name = " PYS_CREATED_DATE")
    private LocalDate CreatedAt;

    @Column(name = "PYS_DAY_SALARY")
    private double empDaySalary;

    @Column(name = "PYS_TOTAL_SALARY")
    private double empTotalSalary;


    //  To make Employee PK  As FK IN PAYSLIP Table We Do mapping
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Employee employee;


}
