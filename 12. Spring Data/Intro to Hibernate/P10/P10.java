import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class P10 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.department.name in ('Engineering', " +
                        "'Tool Design', 'Marketing', 'Information Services')", Employee.class);
        List<Employee> employees = query.getResultList();

        for (Employee e : employees) {
            e.setSalary(e.getSalary().multiply(new BigDecimal("1.12")));
            System.out.printf("%s %s ($%.2f)%n",
                    e.getFirstName(), e.getLastName(), e.getSalary());
        }

        em.getTransaction().commit();
    }
}
