package lab5;

import java.util.Arrays;

public class Example5 {
    public static void main(String[] args) {
        runCase(new int[]{1, 2, 3, 4, 5, 6});
        runCase(new int[]{7, 9, 11});
        runCase(new int[]{-8, -5, -2, 0, 13, 18});
    }

    private static void runCase(int[] numbers) {
        System.out.println("Исходный массив: " + Arrays.toString(numbers));
        System.out.println("Четные числа: " + Arrays.toString(filterEvenNumbers(numbers)));
        System.out.println();
    }

    public static int[] filterEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .toArray();
    }
}
