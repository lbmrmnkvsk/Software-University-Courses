package org.example.l17_productsshop.entities;

import java.util.Set;

public class UserSoldProductsDTO {
    private String firstName;
    private String lastName;
    private Set<ProductSoldDTO> soldProducts;

    public UserSoldProductsDTO() {}

    public UserSoldProductsDTO(String firstName, String lastName, Set<ProductSoldDTO> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductSoldDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductSoldDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
