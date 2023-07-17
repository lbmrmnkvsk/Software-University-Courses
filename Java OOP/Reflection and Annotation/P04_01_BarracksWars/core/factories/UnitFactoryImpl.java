package JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.core.factories;

import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces.Unit;
import JavaOOP.ReflectionAnnotationExercise.P04_01_BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"JavaOOP.ReflectionAnnotationExercise.barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
		try {
			Class unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor = unitClass.getDeclaredConstructor(); //конструктор без аргументи
			return constructor.newInstance(); //нов празен обект
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
				 IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
