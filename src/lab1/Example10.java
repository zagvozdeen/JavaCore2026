package lab1;

import java.time.Year;
import java.util.Scanner;

public class Example10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int birthYear = Integer.parseInt(scanner.nextLine());
        int currentYear = Year.now().getValue();
        int age = currentYear - birthYear;

        System.out.println("Ваш возраст: " + age);
    }
}
