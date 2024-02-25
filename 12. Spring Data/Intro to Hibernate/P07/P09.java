import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class P09 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        TypedQuery<Project> query = em.createQuery(
                "select p from Project p order by p.startDate desc", Project.class);
        List<Project> projects = query.setMaxResults(10).getResultList()
                .stream().sorted(Comparator.comparing(Project::getName)).collect(Collectors.toList());

        for (Project project : projects) {
            System.out.printf("Project name: %s%n", project.getName());
            System.out.printf("\tProject Description: %s%n", project.getDescription());
            System.out.printf("\tProject Start Date:%s%n", project.getStartDate());
            System.out.printf("\tProject End Date: %s%n", project.getEndDate());
        }
    }
}
