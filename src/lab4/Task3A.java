package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3A {
    public static void main(String[] args) {
        // При Scanner.nextByte() выявляются InputMismatchException и ArithmeticException.
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите размер массива: ");
            int size = scanner.nextInt();
            byte[] array = new byte[size];

            System.out.println("Введите элементы массива типа byte:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextByte();
            }

            int sum = 0;
            for (byte value : array) {
                sum += value;
                if (sum < Byte.MIN_VALUE || sum > Byte.MAX_VALUE) {
                    throw new ArithmeticException("Сумма вышла за границы диапазона byte");
                }
            }

            byte result = (byte) sum;
            System.out.println("Сумма элементов = " + result);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: ожидалось значение типа byte.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } finally {
            System.out.println("Task3A завершен.");
            scanner.close();
        }
    }
}
