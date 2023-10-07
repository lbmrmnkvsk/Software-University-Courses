package craftsmanLab.core;

import craftsmanLab.models.ApartmentRenovation;
import craftsmanLab.models.Craftsman;


import java.util.*;
import java.util.stream.Collectors;

public class CraftsmanLabImpl implements CraftsmanLab {
    private final List<Craftsman> craftsmen;
    private final List<ApartmentRenovation> apartments;
    private final Map<ApartmentRenovation, Craftsman> map;

    private final List<ApartmentRenovation> unassignedApartments;

    public CraftsmanLabImpl() {
        this.craftsmen = new ArrayList<>();
        this.apartments = new ArrayList<>();
        this.map = new LinkedHashMap<>();

        this.unassignedApartments = new ArrayList<>();
    }

    @Override
    public void addApartment(ApartmentRenovation job) {
        if (this.apartments.stream().anyMatch(a -> a.address.equals(job.address))) {
            throw new IllegalArgumentException();
        }

        this.apartments.add(job);
        this.unassignedApartments.add(job);
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        if (this.craftsmen.contains(craftsman)) {
            throw new IllegalArgumentException();
        }

        this.craftsmen.add(craftsman);

    }

    @Override
    public boolean exists(ApartmentRenovation job) {
        return this.apartments.contains(job);
    }

    @Override
    public boolean exists(Craftsman craftsman) {
        return this.craftsmen.contains(craftsman);
    }

    @Override
    public void removeCraftsman(Craftsman craftsman) {
        if (!this.exists(craftsman) || this.map.containsValue(craftsman)) {
            throw new IllegalArgumentException();
        }

        this.craftsmen.remove(craftsman);

    }

    @Override
    public Collection<Craftsman> getAllCraftsmen() {
        return this.craftsmen;
    }

    @Override
    public void assignRenovations() {
        List<ApartmentRenovation> toRemove = new ArrayList<>();
        for (ApartmentRenovation apartment : this.unassignedApartments) {
            Craftsman worker = this.craftsmen.stream()
                    .min(Comparator.comparing((Craftsman c) -> c.totalEarnings)).orElse(null);
            if (worker == null) {
                break;
            }

            toRemove.add(apartment);
            worker.totalEarnings += (apartment.workHoursNeeded * worker.hourlyRate);
            this.map.put(apartment, worker);
        }

        this.unassignedApartments.removeAll(toRemove);
    }

    @Override
    public Craftsman getContractor(ApartmentRenovation job) {
        if (!this.map.containsKey(job) || this.map.get(job) == null) {
            throw new IllegalArgumentException();
        }
        return this.map.get(job);
    }

    @Override
    public Craftsman getLeastProfitable() {
        if (this.craftsmen.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return this.craftsmen.stream()
                .min(Comparator.comparing((Craftsman c) -> c.totalEarnings)).orElse(null);
    }

    @Override
    public Collection<ApartmentRenovation> getApartmentsByRenovationCost() {
        return this.apartments.stream()
                .sorted(Comparator.comparing((ApartmentRenovation a) -> {
                    if (this.map.containsKey(a)) {
                        Craftsman worker = this.map.get(a);
                        return (a.workHoursNeeded * worker.hourlyRate);
                    } else {
                        return a.workHoursNeeded;
                    }
                }).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ApartmentRenovation> getMostUrgentRenovations(int limit) {
        return this.apartments.stream()
                .sorted(Comparator.comparing((ApartmentRenovation a) -> a.deadline))
                .limit(limit).collect(Collectors.toList());
    }
}
