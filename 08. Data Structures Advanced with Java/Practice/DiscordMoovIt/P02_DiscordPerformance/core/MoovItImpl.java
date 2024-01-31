package core;

import models.Route;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MoovItImpl implements MoovIt {
    private HashMap<String, Route> idToRoute;
    private TreeSet<Route> favorites;
    private TreeSet<Route> allRoutes;

    public MoovItImpl() {
        this.idToRoute = new HashMap<>();

        Comparator<Route> comp1 = Comparator.comparing(Route::getDistance)
                .thenComparing(Route::getPopularity).reversed();
        this.favorites = new TreeSet<>(comp1);

        Comparator<Route> comp2 = Comparator.comparing(Route::getPopularity).reversed()
                .thenComparing(Route::getDistance)
                .thenComparing((Route r) -> r.getLocationPoints().size());
        this.allRoutes = new TreeSet<>(comp2);
    }

    @Override
    public void addRoute(Route route) {
        String id = route.getId();
        if (this.idToRoute.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        this.idToRoute.put(id, route);
        if (route.getIsFavorite()) {
            this.favorites.add(route);
        }
        this.allRoutes.add(route);
    }

    @Override
    public void removeRoute(String id) {
        Route route = this.idToRoute.get(id);
        if (route == null) {
            throw new IllegalArgumentException();
        }

        this.idToRoute.remove(id);
        if (route.getIsFavorite()) {
            this.favorites.remove(route);
        }
        this.allRoutes.remove(route);
    }

    @Override
    public boolean contains(Route route) {
        return this.idToRoute.containsKey(route.getId());
    }

    @Override
    public int size() {
        return this.idToRoute.size();
    }

    @Override
    public Route getRoute(String id) {
        Route route = this.idToRoute.get(id);
        if (route == null) {
            throw new IllegalArgumentException();
        }
        return route;
    }

    @Override
    public void chooseRoute(String id) {
        Route route = this.idToRoute.get(id);
        if (route == null) {
            throw new IllegalArgumentException();
        }

        route.setPopularity(route.getPopularity() + 1);
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        return this.idToRoute.values().stream()
                .filter((Route r) -> {
                    int index1 = r.getLocationPoints().indexOf(startPoint);
                    int index2 = r.getLocationPoints().indexOf(endPoint);
                    return index1 != -1 && index2 != -1 && index1 < index2;
                }).sorted(Comparator.comparing(Route::getIsFavorite).reversed()
                        .thenComparing((Route r) -> {
                            int index1 = r.getLocationPoints().indexOf(startPoint);
                            int index2 = r.getLocationPoints().indexOf(endPoint);
                            return index2 - index1;
                        })
                        .thenComparing(Route::getPopularity).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        return this.favorites.stream()
                .filter((Route r) -> r.getLocationPoints().indexOf(destinationPoint) >= 1)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Route> getTop5RoutesByPopularityThenByDistanceThenByCountOfLocationPoints() {
        return this.allRoutes.stream().limit(5).collect(Collectors.toList());
    }
}
