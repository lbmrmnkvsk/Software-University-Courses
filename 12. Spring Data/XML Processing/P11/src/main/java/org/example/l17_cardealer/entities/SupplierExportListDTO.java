package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportListDTO {
    @XmlElement(name = "supplier")
    private List<SupplierExportDTO> suppliers;

    public SupplierExportListDTO() {
    }

    public SupplierExportListDTO(List<SupplierExportDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierExportDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierExportDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
