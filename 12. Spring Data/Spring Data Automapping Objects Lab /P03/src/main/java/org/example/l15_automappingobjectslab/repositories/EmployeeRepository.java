package org.example.l15_automappingobjectslab.repositories;

import org.example.l15_automappingobjectslab.entities.Employee;
import org.example.l15_automappingobjectslab.entities.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select new org.example.l15_automappingobjectslab.entities.EmployeeDTO(e.firstName, e.lastName, e.salary, (e.manager.lastName)) " +
            "from Employee e where e.birthday < :date order by e.salary desc")
    List<EmployeeDTO> findEmployeesBornBefore(@Param("date") LocalDate date);
}
