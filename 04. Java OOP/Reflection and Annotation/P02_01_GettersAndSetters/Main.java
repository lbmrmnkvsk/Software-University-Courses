package JavaOOP.ReflectionAnnotationLab.P02_01_GettersAndSetters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Class reflectionClass = Reflection.class;

        Method[] declaredMethods = reflectionClass.getDeclaredMethods();
        Arrays.stream(declaredMethods).sorted(Comparator.comparing(Method::getName))
                .forEach(m -> {
                    if (m.getName().startsWith("get")) {
                        System.out.println(String.format("%s will return class %s", m.getName(), m.getReturnType().getTypeName()));
                    } else if (m.getName().startsWith("set") &&
                            m.getParameterTypes().length == 1) {
                        System.out.println(String.format("%s and will set field of class %s", m.getName(),
                                m.getParameterTypes()[0].getTypeName()));
                    }
                });
    }
}
