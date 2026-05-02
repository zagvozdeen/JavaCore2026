package lab7;

import java.io.File;
import java.util.Scanner;

public class Example10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название файла: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            System.out.println("Размер файла в байтах: " + file.length());
        } else {
            System.out.println("Файл не найден: " + file.getAbsolutePath());
        }
    }
}
