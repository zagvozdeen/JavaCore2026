package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example11 {
    public static void main(String[] args) {
        runCase(List.of("кот", "собака", "попугай", "дом"), 3);
        runCase(List.of("Java", "Stream", "API", "Lambda"), 4);
        runCase(List.of("a", "ab", "abc", "abcd"), 2);
    }

    private static void runCase(List<String> strings, int minLength) {
        System.out.println("Исходный список: " + strings);
        System.out.println("Минимальная длина: " + minLength);
        System.out.println("Строки длиннее порога: " + filterLongStrings(strings, minLength));
        System.out.println();
    }

    public static List<String> filterLongStrings(List<String> strings, int minLength) {
        return strings.stream()
                .filter(string -> string.length() > minLength)
                .collect(Collectors.toList());
    }
}
