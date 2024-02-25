import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class P12 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        List<Object[]> departmentSalaries = em.createQuery(
                "select d.name, max(e.salary) " +
                        "from Department d join d.employees e " +
                        "group by d.name " +
                        "having max(e.salary) not between 30000 and 70000", Object[].class)
                .getResultList();

        for (Object[] record : departmentSalaries) {
            System.out.println(record[0] + "   " + record[1]);
        }
    }
}
