package cafe;

import java.util.ArrayList;
import java.util.List;

public class Cafe {
    private String name;
    private int capacity;
    private List<Employee> employees;

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (this.employees.size() < this.capacity) {
            this.employees.add(employee);
        }
    }

    public boolean removeEmployee(String name) {
        Employee employeeToRemove = null;
        for (Employee employee : this.employees) {
            if (employee.getName().equals(name)) {
                employeeToRemove = employee;
            }
        }

        if (this.employees.contains(employeeToRemove)) {
            this.employees.remove(employeeToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Employee getOldestEmployee() {
        int maxAge = 0;
        for (Employee employee : this.employees) {
            if (employee.getAge() > maxAge) {
                maxAge = employee.getAge();
            }
        }

        Employee oldestEmployee = null;
        for (Employee employee : this.employees) {
            if (employee.getAge() == maxAge) {
                oldestEmployee = employee;
            }
        }
        return oldestEmployee;
    }

    public Employee getEmployee(String name) {
        for (Employee employee : this.employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    public int getCount() {
        return this.employees.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employees working at Cafe ").append(this.name).append(":").append(System.lineSeparator());
        for (Employee employee : this.employees) {
            sb.append(employee.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
