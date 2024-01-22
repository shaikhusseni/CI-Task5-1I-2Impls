package com.emp.attend.sal.Mapping.Repositorys;


import com.emp.attend.sal.Mapping.Entitys.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
