package lab3;

import java.util.HashMap;
import java.util.Map;

// Заполнить HashMap 10 объектами <Integer, String>. Найти строки у которых ключ > 5.
// Если ключ = 0, вывести строки через запятую. Перемножить все ключи, где длина строки > 5.
public class Example9 {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "привет");
        map.put(3, "машина");
        map.put(4, "кот");
        map.put(5, "яблоко");
        map.put(6, "программа");
        map.put(7, "дом");
        map.put(8, "тетрадь");
        map.put(9, "университет");

        System.out.println("Строки, у которых ключ > 5:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() > 5) {
                System.out.println(entry.getValue());
            }
        }

        System.out.println();

        System.out.print("Строки, у которых ключ = 0: ");
        boolean first = true;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == 0) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(entry.getValue());
                first = false;
            }
        }

        System.out.println();
        System.out.println();

        int product = 1;
        boolean found = false;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().length() > 5) {
                product *= entry.getKey();
                found = true;
            }
        }

        if (found) {
            System.out.println("Произведение ключей, где длина строки > 5: " + product);
        } else {
            System.out.println("Нет строк с длиной больше 5.");
        }
    }
}