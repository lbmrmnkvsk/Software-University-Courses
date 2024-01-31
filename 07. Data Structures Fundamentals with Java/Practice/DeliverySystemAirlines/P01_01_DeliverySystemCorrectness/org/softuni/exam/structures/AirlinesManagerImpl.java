package org.softuni.exam.structures;

import org.softuni.exam.entities.Airline;
import org.softuni.exam.entities.Flight;

import java.util.*;
import java.util.stream.Collectors;

public class AirlinesManagerImpl implements AirlinesManager {
    private List<Airline> airlines;
    private List<Flight> flights;
    private Map<Airline, List<Flight>> map;

    public AirlinesManagerImpl() {
        this.airlines = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void addAirline(Airline airline) {
        this.airlines.add(airline);
        this.map.put(airline, new ArrayList<>());
    }

    @Override
    public void addFlight(Airline airline, Flight flight) {
        if (!this.contains(airline)) {
            throw new IllegalStateException();
        }

        this.flights.add(flight);
        this.map.get(airline).add(flight);
    }

    @Override
    public boolean contains(Airline airline) {
        return this.airlines.contains(airline);
    }

    @Override
    public boolean contains(Flight flight) {
        return this.flights.contains(flight);
    }

    @Override
    public void deleteAirline(Airline airline) throws IllegalArgumentException {
        if (!this.contains(airline)) {
            throw new IllegalArgumentException();
        }

        this.airlines.remove(airline);
        List<Flight> flightsToRemove = this.map.get(airline);

        for (Flight f : flightsToRemove) {
            this.flights.remove(f);
        }

        this.map.remove(airline);
    }

    @Override
    public Iterable<Flight> getAllFlights() {
        return this.flights;
    }

    @Override
    public Flight performFlight(Airline airline, Flight flight) throws IllegalArgumentException {
        if (!this.contains(airline) || !this.contains(flight)) {
            throw new IllegalArgumentException();
        }

        flight.setCompleted(true);
        return flight;
    }

    @Override
    public Iterable<Flight> getCompletedFlights() {
        return this.flights.stream().filter(Flight::isCompleted).collect(Collectors.toList());
    }

    @Override
    public Iterable<Flight> getFlightsOrderedByNumberThenByCompletion() {
        return this.flights.stream()
                .sorted(Comparator.comparing(Flight::isCompleted)
                        .thenComparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesOrderedByRatingThenByCountOfFlightsThenByName() {
        return this.airlines.stream()
                .sorted(Comparator.comparing(Airline::getRating).reversed()
                        .thenComparing((Airline a) -> this.map.get(a).size()).reversed()
                        .thenComparing(Airline::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Airline> getAirlinesWithFlightsFromOriginToDestination(String origin, String destination) {
        return this.airlines.stream()
                .filter((Airline a) -> this.map.get(a).stream().
                        anyMatch((Flight f) -> !f.isCompleted() && f.getOrigin().equals(origin) && f.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
