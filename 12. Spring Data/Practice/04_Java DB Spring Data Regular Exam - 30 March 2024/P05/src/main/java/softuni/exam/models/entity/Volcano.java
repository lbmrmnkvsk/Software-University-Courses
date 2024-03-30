package softuni.exam.models.entity;

import softuni.exam.models.enums.VolcanoType;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    @Positive
    private Integer elevation;
    @Enumerated(EnumType.STRING)
    private VolcanoType volcanoType;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "last_eruption")
    private LocalDate lastEruption;
    @OneToMany(mappedBy = "volcano", fetch = FetchType.EAGER)
    private Set<Volcanologist> volcanologists = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Volcano() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public Set<Volcanologist> getVolcanologists() {
        return volcanologists;
    }

    public void setVolcanologists(Set<Volcanologist> volcanologists) {
        this.volcanologists = volcanologists;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
