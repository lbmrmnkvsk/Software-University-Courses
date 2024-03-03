package org.example.l15_automappingobjectslab.entities;

import java.util.List;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> employees;
    private int employeesCount;


    public ManagerDTO(String firstName, String lastName, List<EmployeeDTO> employees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employees = employees;
        this.employeesCount = employees != null ? employees.size() : 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }
}
