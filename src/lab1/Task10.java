package lab1;

import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = Double.parseDouble(scanner.nextLine());
        double b = Double.parseDouble(scanner.nextLine());

        double sum = a + b;
        double diff = a - b;

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + diff);
    }
}
