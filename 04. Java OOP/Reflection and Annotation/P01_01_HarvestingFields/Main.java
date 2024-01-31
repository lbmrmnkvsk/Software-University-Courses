package JavaOOP.ReflectionAnnotationExercise.P01_01_HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String command = scanner.nextLine();
		Class myClass = RichSoilLand.class;
		Field[] fields = myClass.getDeclaredFields();
		Consumer<Field> printField = field -> System.out.printf("%s %s %s%n",
				Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());

		while (!command.equals("HARVEST")) {
			if (command.equals("private")) {
				Arrays.stream(fields)
						.filter(field -> Modifier.isPrivate(field.getModifiers()))
						.forEach(printField);
			} else if (command.equals("protected")) {
				Arrays.stream(fields)
						.filter(field -> Modifier.isProtected(field.getModifiers()))
						.forEach(printField);
			} else if (command.equals("public")) {
				Arrays.stream(fields)
						.filter(field -> Modifier.isPublic(field.getModifiers()))
						.forEach(printField);
			} else if (command.equals("all")) {
				for (Field field : fields) {
					printField.accept(field);
				}
			}

			command = scanner.nextLine();
		}
	}
}
