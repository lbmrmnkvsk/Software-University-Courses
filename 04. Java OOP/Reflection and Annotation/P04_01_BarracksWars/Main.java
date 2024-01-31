package JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars;

import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces.Repository;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces.Runnable;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces.UnitFactory;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.core.Engine;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.core.factories.UnitFactoryImpl;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
