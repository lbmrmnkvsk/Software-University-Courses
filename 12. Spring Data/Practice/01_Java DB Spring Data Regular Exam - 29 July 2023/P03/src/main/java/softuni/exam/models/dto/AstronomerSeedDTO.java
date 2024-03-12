package softuni.exam.models.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerSeedDTO {
    @XmlElement(name = "average_observation_hours")
    @DecimalMin(value = "500")
    private Double averageObservationHours;
    private String birthday;
    @Size(min = 2, max = 30)
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    private String lastName;
    @DecimalMin(value = "15000")
    private Double salary;
    @XmlElement(name = "observing_star_id")
    private Long observingStarId;

    public AstronomerSeedDTO() {
    }

    public AstronomerSeedDTO(Double averageObservationHours, String birthday, String firstName, String lastName, Double salary, Long observingStarId) {
        this.averageObservationHours = averageObservationHours;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.observingStarId = observingStarId;
    }

    public Double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(Double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getObservingStarId() {
        return observingStarId;
    }

    public void setObservingStarId(Long observingStarId) {
        this.observingStarId = observingStarId;
    }
}
