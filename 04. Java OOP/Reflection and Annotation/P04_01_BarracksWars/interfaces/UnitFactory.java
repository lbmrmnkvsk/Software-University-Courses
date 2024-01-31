package JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface UnitFactory {

    Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException;
}