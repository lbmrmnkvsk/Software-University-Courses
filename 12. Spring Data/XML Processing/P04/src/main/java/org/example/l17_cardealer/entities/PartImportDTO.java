package org.example.l17_cardealer.entities;

import jakarta.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDTO {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Double price;
    @XmlAttribute
    private Integer quantity;

    public PartImportDTO() {
    }

    public PartImportDTO(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
