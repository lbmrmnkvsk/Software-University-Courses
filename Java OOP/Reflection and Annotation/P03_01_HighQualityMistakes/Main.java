package JavaOOP.ReflectionAnnotationLab.P03_01_HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Class reflection = Reflection.class;
        Field[] fields = reflection.getDeclaredFields();
        Arrays.stream(fields).sorted(Comparator.comparing(Field::getName))
                .forEach(f -> {
                    boolean isPrivate = Modifier.isPrivate(f.getModifiers());
                    if (!isPrivate) {
                        System.out.printf("%s must be private!%n", f.getName());
                    }
                });

        Method[] methods = reflection.getDeclaredMethods();
        Arrays.stream(methods).sorted(Comparator.comparing(Method::getName))
                .forEach(m -> {
                    if (m.getName().startsWith("get") && m.getParameterCount() == 0 &&
                    !Modifier.isPublic(m.getModifiers())) {
                        System.out.printf("%s have to be public!%n", m.getName());
                    } else if (m.getName().startsWith("set") && m.getParameterCount() == 1 &&
                    !Modifier.isPrivate(m.getModifiers())) {
                        System.out.printf("%s have to be private!%n", m.getName());
                    }
                });
    }
}
