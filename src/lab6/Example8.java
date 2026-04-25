package lab6;

import java.util.Arrays;

public class Example8 {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {14, 7, 32, 5, 19, 48, 11, 3, 27, 41};

        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Максимальный элемент: " + findMaxParallel(array));
    }

    public static int findMaxParallel(int[] array) throws InterruptedException {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        int threadCount = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[threadCount];
        int[] partialMax = new int[threadCount];

        for (int i = 0; i < threadCount; i++) {
            partialMax[i] = Integer.MIN_VALUE;
            int index = i;

            threads[i] = new Thread(() -> {
                int start = index * array.length / threadCount;
                int end = (index + 1) * array.length / threadCount;
                int localMax = Integer.MIN_VALUE;

                for (int j = start; j < end; j++) {
                    if (array[j] > localMax) {
                        localMax = array[j];
                    }
                }

                partialMax[index] = localMax;
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int max = partialMax[0];
        for (int value : partialMax) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}
