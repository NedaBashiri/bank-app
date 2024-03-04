package reflections;

import annotations.DeprecatedMethod;
import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingsAccount;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DeprecatedMethodAnalyzer {

    public static void analyze() {
        List<Class<?>> classes = Arrays.asList(
                BankAccount.class,
                SavingsAccount.class,
                CheckingAccount.class
        );

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz.getName());
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DeprecatedMethod.class)) {
                    DeprecatedMethod annotation = method.getAnnotation(DeprecatedMethod.class);
                    System.out.println("    Method: " + method.getName());
                    System.out.println("        Reason: " + annotation.reason());
                    System.out.println("        Replacement: " + annotation.replacement());
                }
            }
        }
    }
}
