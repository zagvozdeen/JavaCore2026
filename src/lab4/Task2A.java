package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task2A {
    public static void main(String[] args) {
        // При Scanner.nextInt() выявляются InputMismatchException и ArrayIndexOutOfBoundsException.
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите количество строк: ");
            int rows = scanner.nextInt();
            System.out.print("Введите количество столбцов: ");
            int cols = scanner.nextInt();

            int[][] matrix = new int[rows][cols];
            System.out.println("Введите элементы матрицы:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            System.out.print("Введите номер столбца (от 0 до " + (cols - 1) + "): ");
            int column = scanner.nextInt();

            System.out.println("Элементы выбранного столбца:");
            for (int i = 0; i < rows; i++) {
                System.out.println(matrix[i][column]);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: ожидалось целое число.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: столбца с таким номером не существует.");
        } finally {
            System.out.println("Task2A завершен.");
            scanner.close();
        }
    }
}
