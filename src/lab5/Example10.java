package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example10 {
    public static void main(String[] args) {
        runCase(List.of(3, 6, 9, 12, 15), 3);
        runCase(List.of(10, 11, 12, 13, 14, 15), 5);
        runCase(List.of(-12, -8, -5, 0, 4, 16), 4);
    }

    private static void runCase(List<Integer> numbers, int divisor) {
        System.out.println("Исходный список: " + numbers);
        System.out.println("Делитель: " + divisor);
        System.out.println("Числа без остатка: " + filterDivisibleNumbers(numbers, divisor));
        System.out.println();
    }

    public static List<Integer> filterDivisibleNumbers(List<Integer> numbers, int divisor) {
        return numbers.stream()
                .filter(number -> number % divisor == 0)
                .collect(Collectors.toList());
    }
}
