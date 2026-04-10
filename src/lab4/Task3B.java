package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3B {
    public static void main(String[] args) {
        // При Byte.parseByte() выявляются NumberFormatException и ArithmeticException.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите размер массива: ");
            int size = Integer.parseInt(reader.readLine());
            byte[] array = new byte[size];

            for (int i = 0; i < size; i++) {
                System.out.print("Введите элемент [" + i + "]: ");
                array[i] = Byte.parseByte(reader.readLine());
            }

            int sum = 0;
            for (byte value : array) {
                int nextSum = sum + value;
                if (nextSum < Byte.MIN_VALUE || nextSum > Byte.MAX_VALUE) {
                    throw new ArithmeticException("Сумма вышла за границы диапазона byte");
                }
                sum = nextSum;
            }

            System.out.println("Сумма элементов = " + sum);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: значение не приводится к типу byte/int.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } finally {
            System.out.println("Task3B завершен.");
        }
    }
}
