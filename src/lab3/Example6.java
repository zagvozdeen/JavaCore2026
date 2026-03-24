package lab3;

import java.util.Scanner;

// Задача: создать приложение с использованием рекурсии для перевода целого числа,
// введенного с клавиатуры, в двоичную систему счисления.
public class Example6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        int number = scanner.nextInt();

        if (number == 0) {
            System.out.println("Двоичный вид: 0");
        } else if (number < 0) {
            System.out.print("Двоичный вид: -");
            toBinary(-number);
            System.out.println();
        } else {
            System.out.print("Двоичный вид: ");
            toBinary(number);
            System.out.println();
        }

        scanner.close();
    }

    public static void toBinary(int n) {
        if (n > 1) {
            toBinary(n / 2);
        }
        System.out.print(n % 2);
    }
}