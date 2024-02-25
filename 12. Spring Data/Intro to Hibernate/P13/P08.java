import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

public class P08 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.id = :id", Employee.class);
        query.setParameter("id", id);
        Employee e = query.getSingleResult();
        Set<Project> projectsSet = e.getProjects();
        List<Project> projects = projectsSet.stream().sorted(Comparator.comparing(Project::getName)).collect(Collectors.toList());

        System.out.printf("%s %s - %s%n", e.getFirstName(), e.getLastName(), e.getJobTitle());
        for (Project p : projects) {
            System.out.println("      " + p.getName());
        }
    }
}
