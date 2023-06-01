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
        Cloth clothToRemove = null;
        for (Cloth cloth : this.data) {
            if (cloth.getColor().equals(color)) {
                clothToRemove = cloth;
            }
        }

        if (this.data.contains(clothToRemove)) {
            this.data.remove(clothToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Cloth getSmallestCloth() {
        int smallestSize = Integer.MAX_VALUE;
        for (Cloth cloth : this.data) {
            int currentClothSize = cloth.getSize();
            if (currentClothSize < smallestSize) {
                smallestSize = currentClothSize;
            }
        }

        Cloth smallestCloth = null;
        for (Cloth cloth : this.data) {
            if (cloth.getSize() == smallestSize) {
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
        sb.append(this.type).append(" magazine contains:").append(System.lineSeparator());
        for (Cloth cloth : this.data) {
            sb.append(cloth.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
