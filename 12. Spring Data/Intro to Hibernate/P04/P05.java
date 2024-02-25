import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class P05 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.department.name = 'Research and Development' " +
                        "order by e.salary, e.id", Employee.class);
        List<Employee> employees = query.getResultList();

        for (Employee e : employees) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
        }
    }
}
