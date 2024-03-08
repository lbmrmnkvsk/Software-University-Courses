package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.example.l17_cardealer.adapters.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDTO {
    @XmlAttribute
    private String name;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

    public CustomerImportDTO() {
    }

    public CustomerImportDTO(String name, LocalDate birthDate, Boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
