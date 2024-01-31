package magazine;

import java.util.ArrayList;
import java.util.List;

public class Magazine {
    private String type;
    private int capacity;
    private List<Cloth> data;

    public Magazine(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addCloth(Cloth cloth) {
        if (this.data.size() < this.capacity) {
            this.data.add(cloth);
        }
    }

    public boolean removeCloth(String color) {
        for (Cloth cloth : this.data) {
            if (cloth.getColor().equals(color)) {
                this.data.remove(cloth);
                return true;
            }
        }
        return false;
    }

    public Cloth getSmallestCloth() {
        Cloth smallestCloth = new Cloth("", Integer.MAX_VALUE, "");
        for (Cloth cloth : this.data) {
            if (cloth.getSize() < smallestCloth.getSize()) {
                smallestCloth = cloth;
            }
        }
        return smallestCloth;
    }

    public Cloth getCloth(String color) {
        for (Cloth cloth : this.data) {
            if (cloth.getColor().equals(color)) {
                return cloth;
            }
        }
        return null;
    }

    public int getCount() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s magazine contains:", this.type)).append(System.lineSeparator());
        for (Cloth cloth : this.data) {
            sb.append(cloth.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
