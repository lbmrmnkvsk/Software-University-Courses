package org.example.l17_cardealer.services;

import jakarta.persistence.EntityManager;
import org.example.l17_cardealer.entities.Part;
import org.example.l17_cardealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final EntityManager entityManager;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, EntityManager entityManager) {
        this.partRepository = partRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void importParts(List<Part> parts) {
        this.partRepository.saveAll(parts);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Part> getAllParts() {
        return this.partRepository.findAll();
    }

    @Override
    @Transactional
    public void savePart(Part part) {
        this.partRepository.save(part);
    }

    @Override
    @Transactional
    public void mergeParts(List<Part> parts) {
        for (Part part : parts) {
            this.entityManager.merge(part);
        }
    }
}
