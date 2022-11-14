package com.springboot.cruddemo.service;

import com.springboot.cruddemo.converter.EmployeeConverter;
import com.springboot.cruddemo.dao.EmployeeRepository;
import com.springboot.cruddemo.dto.EmployeeDTO;
import com.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;


    @Autowired
    EmployeeConverter employeeConverter;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee;

        if(result.isPresent())
            employee = result.get();
        else
            throw new IllegalArgumentException("Did not find employee id = " + id);

        return employee;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeConverter.convertDtoToEntity(employeeDTO);
        employeeRepository.save(employee);
        return employeeConverter.convertEntityToDto(employee);
    }


    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
