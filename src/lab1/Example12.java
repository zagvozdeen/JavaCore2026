package lab1;

import java.time.Year;
import java.util.Scanner;

public class Example12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        int currentYear = Year.now().getValue();
        int birthYear = currentYear - age;

        System.out.println("Год рождения: " + birthYear);
    }
}
