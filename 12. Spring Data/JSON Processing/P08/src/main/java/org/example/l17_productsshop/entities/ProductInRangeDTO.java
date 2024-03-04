package org.example.l17_productsshop.entities;

public class ProductInRangeDTO {
    private String name;
    private Double price;
    private String seller;

    public ProductInRangeDTO() {}

    public ProductInRangeDTO(String name, Double price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
