package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierListDTO {
    @XmlElement(name = "supplier")
    private List<SupplierImportDTO> suppliers;

    public SupplierListDTO() {
    }

    public SupplierListDTO(List<SupplierImportDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierImportDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierImportDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
