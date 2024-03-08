package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartListDTO {
    @XmlElement(name = "part")
    private List<PartImportDTO> parts;

    public PartListDTO() {
    }

    public PartListDTO(List<PartImportDTO> parts) {
        this.parts = parts;
    }

    public List<PartImportDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartImportDTO> parts) {
        this.parts = parts;
    }
}
