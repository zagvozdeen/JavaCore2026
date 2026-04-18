package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example13 {
    public static void main(String[] args) {
        runCase(List.of("Hello", "Привет", "Java17", "code"));
        runCase(List.of("текст", "ещё", "строка!", "мир"));
        runCase(List.of("OnlyLetters", "123", "With-Sign", "Alpha"));
    }

    private static void runCase(List<String> strings) {
        System.out.println("Исходный список: " + strings);
        System.out.println("Только буквы: " + filterOnlyLetters(strings));
        System.out.println();
    }

    public static List<String> filterOnlyLetters(List<String> strings) {
        return strings.stream()
                .filter(string -> !string.isEmpty())
                .filter(string -> string.chars().allMatch(Character::isLetter))
                .collect(Collectors.toList());
    }
}
