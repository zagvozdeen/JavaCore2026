package lab8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class JsonParser {
    private static final String FILE_PATH = "src/lab8/example-json.json";

    public static void main(String[] args) {
        try {
            JSONObject jsonObject = readJson(FILE_PATH);
            JSONArray jsonArray = (JSONArray) jsonObject.get("books");

            printBooks(jsonObject, "Исходный список книг");

            addBookIfAbsent(jsonArray, "Преступление и наказание", "Федор Достоевский", 1866);
            writeJson(jsonObject, FILE_PATH);
            printBooks(jsonObject, "После добавления книги");

            System.out.println("\nПоиск книг автора Лев Толстой:");
            findBooksByAuthor(jsonArray, "Лев Толстой");

            if (deleteBookByTitle(jsonArray, "Мастер и Маргарита")) {
                writeJson(jsonObject, FILE_PATH);
                System.out.println("\nКнига удалена из JSON-файла.");
            }
            printBooks(jsonObject, "После удаления книги");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject readJson(String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        return (JSONObject) obj;
    }

    private static void writeJson(JSONObject jsonObject, String filePath) throws Exception {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
        }
    }

    private static void printBooks(JSONObject jsonObject, String title) {
        System.out.println("\n" + title);
        System.out.println("Корневой элемент: " + jsonObject.keySet().iterator().next());
        JSONArray jsonArray = (JSONArray) jsonObject.get("books");

        for (Object o : jsonArray) {
            printBook((JSONObject) o);
        }
    }

    private static void printBook(JSONObject book) {
        System.out.println("\nТекущий элемент: book");
        System.out.println("Название книги: " + book.get("title"));
        System.out.println("Автор: " + book.get("author"));
        System.out.println("Год издания: " + book.get("year"));
    }

    private static void findBooksByAuthor(JSONArray jsonArray, String author) {
        for (Object item : jsonArray) {
            if (item instanceof JSONObject) {
                JSONObject book = (JSONObject) item;
                if (author.equals(book.get("author"))) {
                    printBook(book);
                }
            }
        }
    }

    private static void addBook(JSONArray jsonArray, String title, String author, int year) {
        JSONObject newBook = new JSONObject();
        newBook.put("title", title);
        newBook.put("author", author);
        newBook.put("year", year);
        jsonArray.add(newBook);
    }

    private static void addBookIfAbsent(JSONArray jsonArray, String title, String author, int year) {
        for (Object item : jsonArray) {
            if (item instanceof JSONObject) {
                JSONObject book = (JSONObject) item;
                if (title.equals(book.get("title"))) {
                    return;
                }
            }
        }

        addBook(jsonArray, title, author, year);
    }

    private static boolean deleteBookByTitle(JSONArray jsonArray, String title) {
        Iterator<?> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject book = (JSONObject) iterator.next();
            if (title.equals(book.get("title"))) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
}
