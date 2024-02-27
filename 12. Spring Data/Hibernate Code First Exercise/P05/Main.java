import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        em.getTransaction().begin();

        em.getTransaction().commit();
    }
}
