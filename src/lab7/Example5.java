package lab7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Example5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFileName = "src/lab7/example5/input.txt";
        String outputFileName = "src/lab7/example5/output.txt";

        File inputFile = new File(inputFileName);
        File parent = inputFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        System.out.print("Введите данные для записи в исходный файл: ");
        String data = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(inputFileName), StandardCharsets.UTF_8))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Ошибка при записи исходного файла: " + e.getMessage());
            return;
        }

        try (InputStream inputStream = new FileInputStream(inputFileName);
             InputStreamReader inputStreamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             OutputStream outputStream = new FileOutputStream(outputFileName);
             OutputStreamWriter outputStreamWriter =
                     new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line.toUpperCase());
                bufferedWriter.newLine();
            }
            System.out.println("Данные записаны в файл: " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }
    }
}
