package lab5;

import java.util.List;
import java.util.stream.Collectors;

public class Example8 {
    public static void main(String[] args) {
        runCase(List.of(1, 2, 3, 4, 5));
        runCase(List.of(-3, -1, 0, 2));
        runCase(List.of(10, 15, 20));
    }

    private static void runCase(List<Integer> numbers) {
        System.out.println("Исходный список: " + numbers);
        System.out.println("Квадраты чисел: " + squareList(numbers));
        System.out.println();
    }

    public static List<Integer> squareList(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());
    }
}
