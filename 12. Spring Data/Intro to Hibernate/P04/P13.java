import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class P13 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();
        em.getTransaction().begin();

        Town town = em.createQuery(
                "select t from Town t where t.name = :townName", Town.class)
                .setParameter("townName", townName).getSingleResult();
        List<Address> addresses = em.createQuery(
                "select a from Address a where a.town.name = :townName", Address.class)
                        .setParameter("townName", townName).getResultList();

        for (Address address : addresses) {
            address.getEmployees().forEach(e -> e.setAddress(null));
            em.remove(address);
        }

        em.remove(town);
        em.getTransaction().commit();

        System.out.printf("%d address(es) in %s deleted%n", addresses.size(), townName);
    }
}
