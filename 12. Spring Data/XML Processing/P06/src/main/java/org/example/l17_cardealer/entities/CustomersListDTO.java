package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersListDTO {
    @XmlElement(name = "customer")
    private List<CustomerImportDTO> customers;

    public CustomersListDTO() {
    }

    public CustomersListDTO(List<CustomerImportDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerImportDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerImportDTO> customers) {
        this.customers = customers;
    }
}
