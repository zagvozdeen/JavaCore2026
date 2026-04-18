package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example12 {
    public static void main(String[] args) {
        runCase(List.of(1, 5, 10, 15, 20), 9);
        runCase(List.of(-5, -1, 0, 1, 2), 0);
        runCase(List.of(100, 50, 75, 25), 60);
    }

    private static void runCase(List<Integer> numbers, int threshold) {
        System.out.println("Исходный список: " + numbers);
        System.out.println("Порог: " + threshold);
        System.out.println("Числа больше порога: " + filterGreaterNumbers(numbers, threshold));
        System.out.println();
    }

    public static List<Integer> filterGreaterNumbers(List<Integer> numbers, int threshold) {
        return numbers.stream()
                .filter(number -> number > threshold)
                .collect(Collectors.toList());
    }
}
