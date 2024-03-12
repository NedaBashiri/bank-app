package reflections;

import annotations.DeprecatedMethod;
import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingsAccount;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DeprecatedMethodAnalyzer {

    public static void analyze() {
        Reflections reflections = new Reflections("project.root.package");
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        List<Class<?>> classes = Arrays.asList(
                BankAccount.class,
                SavingsAccount.class,
                CheckingAccount.class
        );

        classes.stream()
                .flatMap(clazz -> Stream.of(clazz.getDeclaredMethods()))
                .filter(method -> method.isAnnotationPresent(DeprecatedMethod.class))
                .forEach(method -> {
                    DeprecatedMethod annotation = method.getAnnotation(DeprecatedMethod.class);
                    System.out.println("    Method: " + method.getName());
                    System.out.println("        Reason: " + annotation.reason());
                    System.out.println("        Replacement: " + annotation.replacement());
                });

    }
}
