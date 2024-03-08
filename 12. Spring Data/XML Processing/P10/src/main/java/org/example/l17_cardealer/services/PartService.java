package org.example.l17_cardealer.services;

import org.example.l17_cardealer.entities.Part;

import java.util.List;

public interface PartService {
    void importParts(List<Part> parts);
    List<Part> getAllParts();
    void savePart(Part part);
    void mergeParts(List<Part> parts);
}
