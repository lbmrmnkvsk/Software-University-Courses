package org.example.l17_productsshop.entities;

public class ProductImportDTO {
    private String name;
    private Double price;

    public ProductImportDTO() {
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
}
