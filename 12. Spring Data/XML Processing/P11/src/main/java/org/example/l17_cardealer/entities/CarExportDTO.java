package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportDTO {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartExportDTO> parts;

    public CarExportDTO() {
    }

    public CarExportDTO(String make, String model, Long travelledDistance, List<PartExportDTO> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartExportDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartExportDTO> parts) {
        this.parts = parts;
    }
}
