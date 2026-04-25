package lab6;

import java.util.Arrays;

public class Example9 {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {14, 7, 32, 5, 19, 48, 11, 3, 27, 41};

        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Сумма элементов: " + sumParallel(array));
    }

    public static long sumParallel(int[] array) throws InterruptedException {
        int threadCount = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[threadCount];
        long[] partialSums = new long[threadCount];

        for (int i = 0; i < threadCount; i++) {
            int index = i;

            threads[i] = new Thread(() -> {
                int start = index * array.length / threadCount;
                int end = (index + 1) * array.length / threadCount;
                long localSum = 0;

                for (int j = start; j < end; j++) {
                    localSum += array[j];
                }

                partialSums[index] = localSum;
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long totalSum = 0;
        for (long value : partialSums) {
            totalSum += value;
        }

        return totalSum;
    }
}
