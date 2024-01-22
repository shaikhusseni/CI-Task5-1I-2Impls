package com.emp.attend.sal.Mapping.Services.ServiceImpl;

import com.emp.attend.sal.Mapping.Dtos.EmployeeDto;
import com.emp.attend.sal.Mapping.Entitys.Employee;
import com.emp.attend.sal.Mapping.Repositorys.EmployeeRepo;
import com.emp.attend.sal.Mapping.Services.EmployeeService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Converts an Employee DTO to an Employee Entity.
     *
     * @param employeeDto The Employee DTO to be converted.
     * @return An instance of the Employee Entity.
     */
    public Employee convertDtoToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }


    /**
     * Converts an Employee Entity to an Employee DTO.
     *
     * @param employee The Employee Entity to be converted.
     * @return An instance of the Employee DTO.
     */
    public EmployeeDto mapEntityToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }
}