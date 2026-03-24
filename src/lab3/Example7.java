package lab3;

import java.util.Scanner;

// Создать приложение, позволяющее ввести и вывести одномерный массив целых чисел.
// Для ввода и вывода массива разработать рекурсивные методы вместо циклов for.
public class Example7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        inputArray(array, 0, scanner);

        System.out.println("Элементы массива:");
        outputArray(array, 0);

        scanner.close();
    }

    public static void inputArray(int[] array, int index, Scanner scanner) {
        if (index >= array.length) {
            return;
        }
        System.out.print("array[" + index + "] = ");
        array[index] = scanner.nextInt();
        inputArray(array, index + 1, scanner);
    }

    public static void outputArray(int[] array, int index) {
        if (index >= array.length) {
            return;
        }
        System.out.println("array[" + index + "] = " + array[index]);
        outputArray(array, index + 1);
    }
}