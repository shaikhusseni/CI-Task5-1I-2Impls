package com.emp.attend.sal.Mapping.Entitys;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "EMP_EMAIL")
    private String empEmail;

    @Column(name = "EMP_PHNO")
    private String empPhno;

    @Column(name = "EMP_ADDRESS")
    private String empAddress;

    //  To make Employee As FK IN Attendance Table We Do mapping
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Attendance.class)
    @JoinColumn(name = "EMP_ID")
    private List<Attendance> attendanceRecords;


}
