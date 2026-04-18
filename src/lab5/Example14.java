package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example14 {
    public static void main(String[] args) {
        runCase(List.of(1, 5, 10, 15, 20), 10);
        runCase(List.of(-10, -5, 0, 5, 10), 0);
        runCase(List.of(100, 50, 75, 25), 80);
    }

    private static void runCase(List<Integer> numbers, int threshold) {
        System.out.println("Исходный список: " + numbers);
        System.out.println("Порог: " + threshold);
        System.out.println("Числа меньше порога: " + filterLessNumbers(numbers, threshold));
        System.out.println();
    }

    public static List<Integer> filterLessNumbers(List<Integer> numbers, int threshold) {
        return numbers.stream()
                .filter(number -> number < threshold)
                .collect(Collectors.toList());
    }
}
