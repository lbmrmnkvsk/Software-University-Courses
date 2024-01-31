package core;

import models.Package;

import java.util.*;
import java.util.stream.Collectors;

public class PackageManagerImpl implements PackageManager {
    private LinkedHashMap<String, Package> idToPackage;
    private HashSet<String> nameversion;
    private HashMap<String, LinkedHashSet<Package>> parentToChildren;

    public PackageManagerImpl() {
        this.idToPackage = new LinkedHashMap<>();
        this.nameversion = new HashSet<>();
        this.parentToChildren = new HashMap<>();
    }

    @Override
    public void registerPackage(Package _package) {
        String id = _package.getId();
        String nameversion = _package.getName() + _package.getVersion();
        if (this.nameversion.contains(nameversion)) {
            throw new IllegalArgumentException();
        }

        this.idToPackage.put(id, _package);
        this.nameversion.add(nameversion);
    }

    @Override
    public void removePackage(String id) {
        Package _package = this.idToPackage.get(id);
        if (_package == null) {
            throw new IllegalArgumentException();
        }

        this.idToPackage.remove(id);
        String nameversion = _package.getId() + _package.getVersion();
        this.nameversion.remove(nameversion);
        this.parentToChildren.remove(id);
    }

    @Override
    public void addDependency(String packageId, String dependencyId) {
        Package parent = this.idToPackage.get(packageId);
        Package child = this.idToPackage.get(dependencyId);
        if (parent == null || child == null) {
            throw new IllegalArgumentException();
        }

        this.parentToChildren.putIfAbsent(packageId, new LinkedHashSet<>());
        Set<Package> children = this.parentToChildren.get(packageId);
        children.add(child);
    }

    @Override
    public boolean contains(Package _package) {
        return this.idToPackage.containsKey(_package.getId());
    }

    @Override
    public int size() {
        return this.idToPackage.size();
    }

    @Override
    public Iterable<Package> getDependants(Package _package) {
        return this.parentToChildren.get(_package.getId());
    }

    @Override
    public Iterable<Package> getIndependentPackages() {
        return this.idToPackage.values().stream()
                .filter((Package p) -> !this.parentToChildren.containsKey(p.getId()))
                .sorted(Comparator.comparing(Package::getReleaseDate, Comparator.reverseOrder())
                        .thenComparing(Package::getVersion))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Package> getOrderedPackagesByReleaseDateThenByVersion() {
        return this.idToPackage.values().stream()
                .sorted(Comparator.comparing(Package::getReleaseDate, Comparator.reverseOrder())
                        .thenComparing(Package::getVersion))
                .collect(Collectors.toList());
    }
}
