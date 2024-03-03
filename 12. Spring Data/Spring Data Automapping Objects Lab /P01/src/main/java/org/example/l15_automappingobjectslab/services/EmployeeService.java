package org.example.l15_automappingobjectslab.services;

import org.example.l15_automappingobjectslab.entities.Employee;
import org.example.l15_automappingobjectslab.entities.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findEmployeesBornBefore(LocalDate date);
    Employee save(Employee employee);
}
