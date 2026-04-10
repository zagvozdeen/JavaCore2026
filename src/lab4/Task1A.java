package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1A {
    public static void main(String[] args) {
        // При Scanner.nextInt() выявляются InputMismatchException и ArithmeticException
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();
            int[] array = new int[size];

            System.out.println("Введите элементы массива типа int:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            int sum = 0;
            int positiveCount = 0;
            for (int value : array) {
                if (value > 0) {
                    sum += value;
                    positiveCount++;
                }
            }

            if (positiveCount == 0) {
                throw new ArithmeticException("Положительные элементы отсутствуют");
            }

            double average = (double) sum / positiveCount;
            System.out.println("Среднее значение положительных элементов = " + average);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: ожидалось целое число типа int.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } finally {
            System.out.println("Task1A завершен.");
            scanner.close();
        }
    }
}
