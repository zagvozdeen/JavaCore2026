package lab1;

import java.util.Scanner;

public class Example6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String surname = scanner.nextLine();
        String name = scanner.nextLine();
        String patronymic = scanner.nextLine();

        System.out.println("Hello " + surname + ", " + name + ", " + patronymic);
    }
}
