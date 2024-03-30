package softuni.exam.models.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistImportDto {
    @XmlElement(name = "first_name")
    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;
    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;
    @Positive
    @NotNull
    private Double salary;
    @Min(18)
    @Max(80)
    @NotNull
    private Integer age;
    @XmlElement(name = "exploring_from")
    private String exploringFrom;
    @XmlElement(name = "exploring_volcano_id")
    private Long exploringVolcanoId;

    public VolcanologistImportDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(String exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public Long getExploringVolcanoId() {
        return exploringVolcanoId;
    }

    public void setExploringVolcanoId(Long exploringVolcanoId) {
        this.exploringVolcanoId = exploringVolcanoId;
    }
}
