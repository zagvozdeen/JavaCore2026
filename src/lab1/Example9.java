package lab1;

import java.util.Scanner;

public class Example9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int days = Integer.parseInt(scanner.nextLine());

        System.out.println("Месяц " + month + " содержит " + days + " дней.");
    }
}
