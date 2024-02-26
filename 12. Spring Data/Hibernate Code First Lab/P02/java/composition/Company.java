package composition;

import inheritance.Plane;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(mappedBy = "company")
    private Set<Plane> planes;

    public Company() {
        this.planes = new HashSet<>();
    }

    public Company(String name) {
        this.name = name;
        this.planes = new HashSet<>();
    }

    public List<Plane> getPlanes() {
        return this.planes.stream().collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanes(Set<Plane> planes) {
        this.planes = planes;
    }
}
