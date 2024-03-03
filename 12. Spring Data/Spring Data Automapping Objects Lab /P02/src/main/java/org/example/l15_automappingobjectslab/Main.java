package org.example.l15_automappingobjectslab;

import org.example.l15_automappingobjectslab.entities.Employee;
import org.example.l15_automappingobjectslab.entities.EmployeeDTO;
import org.example.l15_automappingobjectslab.entities.ManagerDTO;
import org.example.l15_automappingobjectslab.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.modelmapper.internal.bytebuddy.description.type.TypeVariableToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Component
public class Main implements CommandLineRunner {
    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public Main(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

//        Employee employee = new Employee();
//        employee.setFirstName("John");
//        employee.setLastName("Doe");
//        employee.setSalary(7500.00);
//        employee.setBirthday(LocalDate.of(1990, 1, 1));
//        employee.setAddress("123 Main St");
//
//        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
//
//        System.out.println("First Name: " + employeeDTO.getFirstName());
//        System.out.println("Last Name: " + employeeDTO.getLastName());
//        System.out.println("Salary: " + employeeDTO.getSalary());

        Employee managerJohn = new Employee();
        managerJohn.setFirstName("John");
        managerJohn.setLastName("Doe");
        managerJohn.setSalary(3000.0);
        managerJohn.setBirthday(LocalDate.of(1980, 3, 15));
        managerJohn.setOnHoliday(false);
        managerJohn.setAddress("123 Main St");

        Employee employeeJane = new Employee();
        employeeJane.setFirstName("Jane");
        employeeJane.setLastName("Smith");
        employeeJane.setSalary(2000.0);
        employeeJane.setBirthday(LocalDate.of(1980, 7, 21));
        employeeJane.setOnHoliday(true);
        employeeJane.setAddress("456 Maple Ave");
        employeeJane.setManager(managerJohn);

        Employee employeeEmily = new Employee();
        employeeEmily.setFirstName("Emily");
        employeeEmily.setLastName("Johnson");
        employeeEmily.setSalary(2500.0);
        employeeEmily.setBirthday(LocalDate.of(1985, 11, 30));
        employeeEmily.setOnHoliday(false);
        employeeEmily.setAddress("789 Oak St");
        employeeEmily.setManager(managerJohn);

        // Set subordinates for managerJohn
        managerJohn.setSubordinates(Arrays.asList(employeeJane, employeeEmily));

        // Create a list of employees
        List<Employee> employees = Arrays.asList(managerJohn, employeeJane, employeeEmily);

//        Type listType = new TypeToken<List<EmployeeDTO>>(){}.getType();
//        List<EmployeeDTO> employeeDTOList = modelMapper.map(employees, listType);
//
//        List<ManagerDTO> managerDTOList = employees.stream()
//                .filter(e -> e.getManager() == null && e.getSubordinates() != null)
//                .map(manager -> {
//                    List<EmployeeDTO> subordinatesDTO = manager.getSubordinates().stream()
//                            .map(subordinate -> modelMapper.map(subordinate, EmployeeDTO.class))
//                            .collect(Collectors.toList());
//                    return new ManagerDTO(manager.getFirstName(), manager.getLastName(), subordinatesDTO);
//                }).collect(Collectors.toList());
//
//        managerDTOList.forEach(manager -> {
//            System.out.println(manager.getFirstName() + " " + manager.getLastName() + " | Employees: " + manager.getEmployees().size());
//            manager.getEmployees().forEach(e -> {
//                System.out.println("    - " + e.getFirstName() + " " + e.getLastName() + " " + e.getSalary());
//            });
//        });

        employees.forEach(employeeService::save);

        employeeService.findEmployeesBornBefore(LocalDate.of(2000, 1, 1))
                .forEach(dto -> {
                    String managerInfo = dto.getManagerLastName() == null ? "[no manager]" : dto.getManagerLastName();
                    System.out.println(dto.getFirstName() + " " + dto.getLastName() + " " + dto.getSalary() + " – Manager: " + managerInfo);
                });
    }


}
