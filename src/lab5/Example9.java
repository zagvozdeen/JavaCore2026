package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example9 {
    public static void main(String[] args) {
        runCase(List.of("stream", "substring", "string", "code"), "str");
        runCase(List.of("Москва", "область", "мост", "дом"), "мо");
        runCase(List.of("alpha", "beta", "gamma"), "zz");
    }

    private static void runCase(List<String> strings, String substring) {
        System.out.println("Исходный список: " + strings);
        System.out.println("Подстрока: " + substring);
        System.out.println("Результат: " + filterBySubstring(strings, substring));
        System.out.println();
    }

    public static List<String> filterBySubstring(List<String> strings, String substring) {
        return strings.stream()
                .filter(string -> string.contains(substring))
                .collect(Collectors.toList());
    }
}
