package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportListDTO {
    @XmlElement(name = "customer")
    private List<CustomerExportDTO> customers;

    public CustomerExportListDTO() {
    }

    public CustomerExportListDTO(List<CustomerExportDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerExportDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerExportDTO> customers) {
        this.customers = customers;
    }
}
