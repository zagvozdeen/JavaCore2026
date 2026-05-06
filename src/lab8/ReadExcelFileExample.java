package lab8;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelFileExample {
    public static void main(String[] args) {
        String filePath = "src/lab8/example.xlsx";
        String sheetName = "Товары";

        try (FileInputStream inputStream = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Лист \"" + sheetName + "\" не найден в файле " + filePath + ".");
                System.out.println("Проверьте название листа или создайте файл заново через WriteExcelFileExample.");
                return;
            }

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл Excel не найден: " + filePath);
            System.out.println("Сначала запустите WriteExcelFileExample, чтобы создать пример файла.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения Excel-файла: " + e.getMessage());
            System.out.println("Проверьте, что файл существует, не поврежден и имеет формат XLSX.");
        } catch (RuntimeException e) {
            System.out.println("Не удалось обработать Excel-файл: " + e.getMessage());
        }
    }
}
