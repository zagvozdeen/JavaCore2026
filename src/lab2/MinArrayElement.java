package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MinArrayElement {
    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[15];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(101) - 50;
        }

        System.out.println("Массив: " + Arrays.toString(numbers));

        int minValue = numbers[0];
        List<Integer> minIndexes = new ArrayList<>();
        minIndexes.add(0);

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
                minIndexes.clear();
                minIndexes.add(i);
            } else if (numbers[i] == minValue) {
                minIndexes.add(i);
            }
        }

        System.out.println("Минимальное значение: " + minValue);
        System.out.println("Индексы минимального элемента: " + minIndexes);
    }
}
