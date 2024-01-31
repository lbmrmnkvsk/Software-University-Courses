package JavaOOP.InheritanceLab.P03_01_RandomArrayList;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<T> extends ArrayList<T> {

    private final static Random RANDOM = ThreadLocalRandom.current();

    public T getRandomElement() {
        int randomIndex = RANDOM.nextInt(super.size());
        return super.get(randomIndex);
    }

}
