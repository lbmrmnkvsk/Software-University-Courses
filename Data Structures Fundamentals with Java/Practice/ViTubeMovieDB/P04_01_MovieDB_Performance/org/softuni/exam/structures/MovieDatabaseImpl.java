package org.softuni.exam.structures;

import org.softuni.exam.entities.Actor;
import org.softuni.exam.entities.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieDatabaseImpl implements MovieDatabase {
    private List<Actor> actors;
    private List<Movie> movies;
    private Map<Actor, List<Movie>> map;

    public MovieDatabaseImpl() {
        this.actors = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.map = new LinkedHashMap<>();
    }

    @Override
    public void addActor(Actor actor) {
        this.actors.add(actor);
        this.map.put(actor, new ArrayList<>());
    }

    @Override
    public void addMovie(Actor actor, Movie movie) throws IllegalArgumentException {
        if (!this.contains(actor)) {
            throw new IllegalArgumentException();
        }

        this.movies.add(movie);
        this.map.get(actor).add(movie);
    }

    @Override
    public boolean contains(Actor actor) {
        return this.actors.contains(actor);
    }

    @Override
    public boolean contains(Movie movie) {
        return this.movies.contains(movie);
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return this.movies;
    }

    @Override
    public Iterable<Actor> getNewbieActors() {
        return this.actors.stream().filter((Actor a) -> this.map.get(a).isEmpty()).collect(Collectors.toList());
    }

    @Override
    public Iterable<Movie> getMoviesOrderedByBudgetThenByRating() {
        return this.movies.stream()
                .sorted(Comparator.comparing(Movie::getBudget).reversed()
                        .thenComparing(Movie::getRating).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Actor> getActorsOrderedByMaxMovieBudgetThenByMoviesCount() {
        return null;
    }

    @Override
    public Iterable<Movie> getMoviesInRangeOfBudget(double lower, double upper) {
        return this.movies.stream().sorted(Comparator.comparing(Movie::getRating).reversed())
                .filter(m -> m.getBudget() >= lower && m.getBudget() <= upper)
                .collect(Collectors.toList());
    }
}
