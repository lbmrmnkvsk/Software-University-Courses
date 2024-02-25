import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class P02 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Town> towns = em.createQuery("SELECT t FROM Town t WHERE LENGTH(t.name) <= 5", Town.class).getResultList();
        for (Town town : towns) {
            town.setName(town.getName().toUpperCase());
        }

        em.getTransaction().commit();
    }
}
