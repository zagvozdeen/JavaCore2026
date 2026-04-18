package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example7 {
    public static void main(String[] args) {
        runCase(List.of("Москва", "река", "Волга", "город", "Тула"));
        runCase(List.of("apple", "Banana", "Cherry", "date"));
        runCase(List.of("Один", "Два", "три", "Четыре"));
    }

    private static void runCase(List<String> strings) {
        System.out.println("Исходный список: " + strings);
        System.out.println("Строки с большой буквы: " + filterCapitalizedStrings(strings));
        System.out.println();
    }

    public static List<String> filterCapitalizedStrings(List<String> strings) {
        return strings.stream()
                .filter(string -> !string.isEmpty())
                .filter(string -> Character.isUpperCase(string.charAt(0)))
                .collect(Collectors.toList());
    }
}
