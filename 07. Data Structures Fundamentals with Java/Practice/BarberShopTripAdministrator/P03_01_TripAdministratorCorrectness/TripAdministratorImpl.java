package tripadministratorjava;

import java.util.*;
import java.util.stream.Collectors;

public class TripAdministratorImpl implements TripAdministrator {
    private List<Trip> trips;
    private List<Company> companies;
    private Map<Company, List<Trip>> map;

    public TripAdministratorImpl() {
        this.trips = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.map = new LinkedHashMap<>();
    }

    @Override
    public void addCompany(Company c) {
        if (this.exist(c)) {
            throw new IllegalArgumentException();
        }

        this.companies.add(c);
        this.map.put(c, new ArrayList<>());
    }

    @Override
    public void addTrip(Company c, Trip t) {
        if (!this.exist(c)) {
            throw new IllegalArgumentException();
        }

        if (this.map.get(c).size() < c.tripOrganizationLimit) {
            this.trips.add(t);
            this.map.get(c).add(t);
        }
    }

    @Override
    public boolean exist(Company c) {
        return this.companies.contains(c);
    }

    @Override
    public boolean exist(Trip t) {
        return this.trips.contains(t);
    }

    @Override
    public void removeCompany(Company c) {
        if (!this.exist(c)) {
            throw new IllegalArgumentException();
        }

        List<Trip> tripsToRemove = this.map.get(c);
        for (Trip trip : tripsToRemove) {
            this.trips.remove(trip);
        }

        this.map.remove(c);
    }

    @Override
    public Collection<Company> getCompanies() {
        return Collections.unmodifiableCollection(this.companies);
    }

    @Override
    public Collection<Trip> getTrips() {
        return Collections.unmodifiableCollection(this.trips);
    }

    @Override
    public void executeTrip(Company c, Trip t) {
        if (!this.exist(c) || !this.exist(t) || !this.map.get(c).contains(t)) {
            throw new IllegalArgumentException();
        }

        this.trips.remove(t);
        this.map.get(c).remove(t);
    }

    @Override
    public Collection<Company> getCompaniesWithMoreThatNTrips(int n) {
        return this.companies.stream().filter(c -> this.map.get(c).size() > n).collect(Collectors.toList());
    }

    @Override
    public Collection<Trip> getTripsWithTransportationType(Transportation t) {
        return this.trips.stream().filter(trip -> trip.transportation.equals(t)).collect(Collectors.toList());
    }

    @Override
    public Collection<Trip> getAllTripsInPriceRange(int lo, int hi) {
        return this.trips.stream().filter(t -> t.price >= lo && t.price <= hi).collect(Collectors.toList());
    }
}
