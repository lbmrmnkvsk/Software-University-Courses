package org.example.l17_productsshop.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException;
    void seedCategories() throws IOException;
    void seedProducts() throws IOException;
}
