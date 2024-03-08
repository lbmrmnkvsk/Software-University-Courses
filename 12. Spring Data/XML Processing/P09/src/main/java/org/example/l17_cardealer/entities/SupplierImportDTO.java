package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDTO {
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;

    public SupplierImportDTO() {
    }

    public SupplierImportDTO(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
