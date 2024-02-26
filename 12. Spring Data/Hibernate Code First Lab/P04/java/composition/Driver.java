package composition;

import inheritance.Car;
import inheritance.Truck;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;


    @ManyToMany(mappedBy = "drivers")
    private Set<Truck> trucks;

    public Driver() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(Set<Truck> trucks) {
        this.trucks = trucks;
    }
}
