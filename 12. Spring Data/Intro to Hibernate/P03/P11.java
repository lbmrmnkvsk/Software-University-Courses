import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class P11 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where lower(e.firstName) like :pattern", Employee.class);
        query.setParameter("pattern", pattern.toLowerCase() + "%");
        List<Employee> employees = query.getResultList();

        for (Employee e : employees) {
            System.out.println(e.getFirstName() + " " + e.getLastName());
        }
    }
}
