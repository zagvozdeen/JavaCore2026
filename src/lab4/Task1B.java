package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1B {
    public static void main(String[] args) {
        // При Integer.parseInt() выявляются NumberFormatException и ArithmeticException.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите размер массива: ");
            int size = Integer.parseInt(reader.readLine());
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                System.out.print("Введите элемент [" + i + "]: ");
                array[i] = Integer.parseInt(reader.readLine());
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
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: строка не соответствует типу int.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } finally {
            System.out.println("Task1B завершен.");
        }
    }
}
