package lab7;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Example9 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String inputFileName = "src/lab7/example9/input.txt";
        String outputFileName = "src/lab7/example9/output.txt";

        File inputFile = new File(inputFileName);
        File parent = inputFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        System.out.println("Введите текст для копирования в файл. Пустая строка завершает ввод:");
        try (FileWriter writer = new FileWriter(inputFileName)) {
            while (true) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }

        try (FileReader reader = new FileReader(inputFileName);
             FileWriter writer = new FileWriter(outputFileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }

        System.out.println("Файл скопирован успешно!");
    }
}
