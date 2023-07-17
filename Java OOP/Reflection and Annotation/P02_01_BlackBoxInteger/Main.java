package JavaOOP.ReflectionAnnotationExercise.P02_01_BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class myClass = BlackBoxInt.class;
        Constructor constructor = myClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance();

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] tokens = command.split("_");
            String methodName = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method method = myClass.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            Field field = myClass.getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(blackBoxInt));

            command = scanner.nextLine();
        }
    }
}
