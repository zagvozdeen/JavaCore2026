package lab7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Example8 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = "src/lab7/example8/input.txt";
        File file = new File(fileName);

        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        System.out.println("Введите строки для записи в файл. Пустая строка завершает ввод:");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            while (true) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                writer.write(line);
                writer.newLine();
            }
        }

        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }

        System.out.println("Количество строк в файле: " + lineCount);
    }
}
