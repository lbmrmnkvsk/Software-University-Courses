package core;

import models.Package;

import java.util.*;
import java.util.stream.Collectors;

public class PackageManagerImpl implements PackageManager {
    private HashMap<String, Package> idToPackage;
    private HashSet<String> nameversion;
    private HashMap<String, HashSet<String>> parentToChildren;
    private HashMap<String, String> childToParent;
    private TreeSet<Package> independentPackages;
    private TreeSet<Package> allPackages;

    public PackageManagerImpl() {
        this.idToPackage = new HashMap<>();
        this.nameversion = new HashSet<>();
        this.parentToChildren = new HashMap<>();
        this.childToParent = new HashMap<>();
        Comparator<Package> comp = Comparator.comparing(Package::getReleaseDate).reversed()
                .thenComparing(Package::getVersion);
        this.independentPackages = new TreeSet<>(comp);
        this.allPackages = new TreeSet<>(comp);
    }

    @Override
    public void registerPackage(Package _package) {
        String nameversion = _package.getName() + _package.getVersion();
        if (this.nameversion.contains(nameversion)) {
            throw new IllegalArgumentException();
        }

        String id = _package.getId();

        this.idToPackage.put(id, _package);
        this.nameversion.add(nameversion);
        this.independentPackages.add(_package);
        this.allPackages.add(_package);
    }

    @Override
    public void removePackage(String packageId) {
        if (!this.idToPackage.containsKey(packageId)) {
            throw new IllegalArgumentException();
        }

        Package _package = this.idToPackage.get(packageId);
        String nameversion = _package.getName() + _package.getVersion();

        this.idToPackage.remove(packageId);
        this.nameversion.remove(nameversion);
        this.independentPackages.remove(_package);
        this.allPackages.remove(_package);

        Set<String> children = this.parentToChildren.get(packageId);
        this.parentToChildren.remove(packageId);

        if (children != null) {
            for (String child : children) {
                this.childToParent.remove(child);
            }
        }
    }

    @Override
    public void addDependency(String packageId, String dependencyId) {
        if (!this.idToPackage.containsKey(packageId) || !this.idToPackage.containsKey(dependencyId)) {
            throw new IllegalArgumentException();
        }

        this.parentToChildren.putIfAbsent(packageId, new HashSet<>());
        Set<String> children = this.parentToChildren.get(packageId);
        children.add(dependencyId);

        this.childToParent.put(dependencyId, packageId);

        Package child = this.idToPackage.get(dependencyId);
        Package parent = this.idToPackage.get(packageId);
        this.independentPackages.remove(child);
        this.independentPackages.remove(parent);
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
        List<String> ids = new ArrayList<>();
        String id = _package.getId();

        String parent = this.childToParent.get(id);
        if (parent != null) {
            ids.add(parent);
        }

        Set<String> children = this.parentToChildren.get(id);
        if (children != null) {
            for (String child : children) {
                ids.add(child);
            }
        }

        return ids.stream().map(item -> this.idToPackage.get(item)).collect(Collectors.toList());
    }

    @Override
    public Iterable<Package> getIndependentPackages() {
        return this.independentPackages;
    }

    @Override
    public Iterable<Package> getOrderedPackagesByReleaseDateThenByVersion() {
        // Group packages by name
        Map<String, List<Package>> packagesByName = allPackages.stream()
                .collect(Collectors.groupingBy(Package::getName));

        // Create a list to store the latest version of each package
        List<Package> latestVersions = new ArrayList<>();

        // Iterate over each group and find the latest version
        for (List<Package> packagesWithSameName : packagesByName.values()) {
            Package latestVersion = packagesWithSameName.stream()
                    .max(Comparator.comparing(Package::getVersion))
                    .orElseThrow(NoSuchElementException::new);
            latestVersions.add(latestVersion);
        }

        // Sort the list of latest versions by release date in descending order
        latestVersions.sort(Comparator.comparing(Package::getReleaseDate).reversed());

        return latestVersions;
    }
}
