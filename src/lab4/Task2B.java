package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2B {
    public static void main(String[] args) {
        // При Integer.parseInt() выявляются NumberFormatException и ArrayIndexOutOfBoundsException.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите количество строк: ");
            int rows = Integer.parseInt(reader.readLine());
            System.out.print("Введите количество столбцов: ");
            int cols = Integer.parseInt(reader.readLine());

            int[][] matrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                System.out.println("Введите строку матрицы " + (i + 1) + ":");
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Integer.parseInt(reader.readLine());
                }
            }

            System.out.print("Введите номер столбца (от 1 до " + cols + "): ");
            int columnNumber = Integer.parseInt(reader.readLine());
            int columnIndex = columnNumber - 1;

            System.out.println("Элементы выбранного столбца:");
            for (int i = 0; i < rows; i++) {
                System.out.println(matrix[i][columnIndex]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода: строка не соответствует целому числу.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: столбца с таким номером не существует.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } finally {
            System.out.println("Task2B завершен.");
        }
    }
}
