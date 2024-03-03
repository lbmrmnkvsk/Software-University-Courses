package org.example.l15_automappingobjectslab.services;

import org.example.l15_automappingobjectslab.entities.Employee;
import org.example.l15_automappingobjectslab.entities.EmployeeDTO;
import org.example.l15_automappingobjectslab.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeDTO> findEmployeesBornBefore(LocalDate date) {
        return this.employeeRepository.findEmployeesBornBefore(date);
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }
}
