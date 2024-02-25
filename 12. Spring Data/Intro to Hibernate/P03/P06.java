import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class P06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        String lastName = scanner.nextLine();
        em.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        em.persist(newAddress);

        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.lastName = :name", Employee.class);
        query.setParameter("name", lastName);
        List<Employee> employees = query.getResultList();

        for (Employee e : employees) {
            e.setAddress(newAddress);
        }

        em.getTransaction().commit();
    }
}
