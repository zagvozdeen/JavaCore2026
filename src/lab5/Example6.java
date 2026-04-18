package lab5;

import java.util.Arrays;

public class Example6 {
    public static void main(String[] args) {
        runCase(
                new int[]{1, 2, 3, 4, 5},
                new int[]{3, 4, 5, 6, 7}
        );
        runCase(
                new int[]{10, 20, 30},
                new int[]{1, 2, 3}
        );
        runCase(
                new int[]{5, 5, 7, 8},
                new int[]{5, 8, 9, 10}
        );
    }

    private static void runCase(int[] first, int[] second) {
        System.out.println("Первый массив: " + Arrays.toString(first));
        System.out.println("Второй массив: " + Arrays.toString(second));
        System.out.println("Общие элементы: " + Arrays.toString(findCommonElements(first, second)));
        System.out.println();
    }

    public static int[] findCommonElements(int[] first, int[] second) {
        return Arrays.stream(first)
                .filter(value -> Arrays.stream(second).anyMatch(candidate -> candidate == value))
                .toArray();
    }
}
