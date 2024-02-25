import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class P07 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        TypedQuery<Address> query = em.createQuery("select a from Address a order by size(a.employees) desc", Address.class);
        List<Address> addresses = query.setMaxResults(10).getResultList();

        for (Address a : addresses) {
            System.out.printf("%s, %s - %d employees%n",
                    a.getText(), a.getTown().getName(), a.getEmployees().size());
        }
    }
}
