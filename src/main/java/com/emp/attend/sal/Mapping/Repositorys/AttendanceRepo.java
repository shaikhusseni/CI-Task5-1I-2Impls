package com.emp.attend.sal.Mapping.Repositorys;

import com.emp.attend.sal.Mapping.Entitys.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    /**
     * @param empId : based on empId check  if present
     * @return Attendance added
     */
    List<Attendance> findByEmployee_EmpId(Long empId);


}
