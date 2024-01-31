package renovation.core;

import renovation.models.Laminate;
import renovation.models.Tile;
import renovation.models.WoodType;

import java.util.*;
import java.util.stream.Collectors;

public class RenovationImpl implements Renovation {
    private final Deque<Tile> tiles;
    private final Deque<Laminate> laminates;
    private double deliveredTileArea;

    public RenovationImpl() {
        this.tiles = new ArrayDeque<>();
        this.laminates = new ArrayDeque<>();
        this.deliveredTileArea = 0;
    }
    @Override
    public void deliverTile(Tile tile) {
        if (this.deliveredTileArea + getTileArea(tile) > 30) {
            throw new IllegalArgumentException();
        }

        this.tiles.push(tile);
        this.deliveredTileArea += getTileArea(tile);
    }

    private double getTileArea(Tile tile) {
        return tile.width * tile.height;
    }

    @Override
    public void deliverFlooring(Laminate laminate) {
        this.laminates.push(laminate);
    }

    @Override
    public double getDeliveredTileArea() {
        return this.deliveredTileArea;
    }

    @Override
    public boolean isDelivered(Laminate laminate) {
        return this.laminates.contains(laminate);
    }

    @Override
    public void returnTile(Tile tile) {
        if (!this.tiles.contains(tile)) {
            throw new IllegalArgumentException();
        }

        this.tiles.removeLastOccurrence(tile);
        this.deliveredTileArea -= (tile.width * tile.height);
    }

    @Override
    public void returnLaminate(Laminate laminate) {
        if (!this.isDelivered(laminate)) {
            throw new IllegalArgumentException();
        }

        this.laminates.removeLastOccurrence(laminate);
    }

    @Override
    public Collection<Laminate> getAllByWoodType(WoodType wood) {
        return this.laminates.stream().filter(l -> l.woodType.equals(wood)).collect(Collectors.toList());
    }

    @Override
    public Collection<Tile> getAllTilesFitting(double width, double height) {
        return this.tiles.stream().filter(t -> t.width == width && t.height == height).collect(Collectors.toList());
    }

    @Override
    public Collection<Tile> sortTilesBySize() {
        return this.tiles.stream()
                .sorted(Comparator.comparing((Tile t) -> (t.height * t.width))
                        .thenComparing((Tile t) -> t.depth))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Laminate> layFlooring() {
        return new Iterator<Laminate>() {
            private final Deque<Laminate> stack = laminates;
            @Override
            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override
            public Laminate next() {
                return this.stack.pop();
            }
        };
    }
}
