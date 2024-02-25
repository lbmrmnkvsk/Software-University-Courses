import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Scanner;

public class P03 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        String employeeFullName = scanner.nextLine();

        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Employee e where concat(e.firstName, ' ', e.lastName) = :fullName", Long.class);
        query.setParameter("fullName", employeeFullName);
        long count = query.getSingleResult();

        if (count > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
