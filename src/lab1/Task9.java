package lab1;

import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int first = n - 1;
        int second = n;
        int third = n + 1;
        int fourth = (first + second + third) * (first + second + third);

        System.out.println(first + " " + second + " " + third + " " + fourth);
    }
}
