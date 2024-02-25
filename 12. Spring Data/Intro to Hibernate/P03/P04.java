import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class P04 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.salary > 50000", Employee.class);
        List<Employee> employees = query.getResultList();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }
    }
}
