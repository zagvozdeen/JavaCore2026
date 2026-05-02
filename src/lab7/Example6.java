package lab7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Example6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFileName = "src/lab7/example6/input.txt";
        String outputFileName = "src/lab7/example6/output.txt";

        File inputFile = new File(inputFileName);
        File parent = inputFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        System.out.print("Введите данные для записи в исходный файл: ");
        String data = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFileName))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Ошибка при записи исходного файла: " + e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(inputFileName));
             PrintWriter printWriter =
                     new PrintWriter(outputFileName, StandardCharsets.UTF_8)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line.toUpperCase());
            }
            System.out.println("Данные записаны в файл: " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }
    }
}
