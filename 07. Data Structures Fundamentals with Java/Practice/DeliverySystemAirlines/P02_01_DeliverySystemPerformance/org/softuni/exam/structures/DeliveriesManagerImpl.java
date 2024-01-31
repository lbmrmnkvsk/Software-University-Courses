package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {
    private List<Deliverer> deliverers = new ArrayList<>();
    private List<Package> packages = new ArrayList<>();
    private List<Package> unassignedPackages = new ArrayList<>();
    private Map<Deliverer, List<Package>> packagesByDeliverers = new HashMap<>();

    public DeliveriesManagerImpl() {
        this.deliverers = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.unassignedPackages = new ArrayList<>();
        this.packagesByDeliverers = new HashMap<>();
    }

    @Override
    public void addDeliverer(Deliverer deliverer) {
        this.deliverers.add(deliverer);
        this.packagesByDeliverers.put(deliverer, new ArrayList<>());
    }

    @Override
    public void addPackage(Package _package) {
        this.packages.add(_package);
        this.unassignedPackages.add(_package);
    }

    @Override
    public boolean contains(Deliverer deliverer) {
        return this.deliverers.contains(deliverer);
    }

    @Override
    public boolean contains(Package _package) {
        return this.packages.contains(_package);
    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
        return this.deliverers;
    }

    @Override
    public Iterable<Package> getPackages() {
        return this.packages;
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {
        if (!this.deliverers.contains(deliverer) || !this.packages.contains(_package)) {
            throw new IllegalArgumentException();
        }

        this.packagesByDeliverers.get(deliverer).add(_package);
        this.unassignedPackages.remove(_package);
    }

    @Override
    public Iterable<Package> getUnassignedPackages() {
        return this.unassignedPackages;
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
        return this.packages.stream()
                .sorted(Comparator.comparing(Package::getWeight).reversed()
                        .thenComparing(Package::getReceiver))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {
        return this.packagesByDeliverers.keySet().stream()
                .sorted(Comparator.comparing((Deliverer d) -> packagesByDeliverers.get(d).size()).reversed()
                        .thenComparing(Deliverer::getName))
                .collect(Collectors.toList());
    }
}
