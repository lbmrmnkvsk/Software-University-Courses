package softuni.exam.models.dto;

import softuni.exam.models.entity.StarType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class StarSeedDTO {
    @Size(min = 6)
    private String description;
    @NotNull
    @Positive
    private Double lightYears;
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    private StarType starType;
    private Long constellation;

    public StarSeedDTO() {
    }

    public StarSeedDTO(String description, Double lightYears, String name, StarType starType, Long constellation) {
        this.description = description;
        this.lightYears = lightYears;
        this.name = name;
        this.starType = starType;
        this.constellation = constellation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLightYears() {
        return lightYears;
    }

    public void setLightYears(Double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Long getConstellation() {
        return constellation;
    }

    public void setConstellation(Long constellation) {
        this.constellation = constellation;
    }
}
