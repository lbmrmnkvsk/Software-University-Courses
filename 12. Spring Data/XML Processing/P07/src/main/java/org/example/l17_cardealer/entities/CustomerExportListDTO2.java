package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportListDTO2 {
    @XmlElement(name = "customer")
    private List<CustomerExportDTO2> customers;

    public CustomerExportListDTO2() {
    }

    public CustomerExportListDTO2(List<CustomerExportDTO2> customers) {
        this.customers = customers;
    }

    public List<CustomerExportDTO2> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerExportDTO2> customers) {
        this.customers = customers;
    }
}
