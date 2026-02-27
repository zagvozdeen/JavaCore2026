package lab1;

import java.util.Map;
import java.util.Scanner;

public class Example8 {
    private static final Map<Integer, String> DAY_MAP = Map.of(
            1, "пн",
            2, "вт",
            3, "ср",
            4, "чт",
            5, "пт",
            6, "сб",
            7, "вс"
    );

    private static final Map<Integer, String> MONTH_MAP = Map.ofEntries(
            Map.entry(1, "январь"),
            Map.entry(2, "февраль"),
            Map.entry(3, "март"),
            Map.entry(4, "апрель"),
            Map.entry(5, "май"),
            Map.entry(6, "июнь"),
            Map.entry(7, "июль"),
            Map.entry(8, "август"),
            Map.entry(9, "сентябрь"),
            Map.entry(10, "октябрь"),
            Map.entry(11, "ноябрь"),
            Map.entry(12, "декабрь")
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dayOfWeekNumber = Integer.parseInt(scanner.nextLine());
        int monthNumber = Integer.parseInt(scanner.nextLine());
        int day = Integer.parseInt(scanner.nextLine());

        String dayOutput = DAY_MAP.getOrDefault(dayOfWeekNumber, "неизвестный-день");
        String monthOutput = MONTH_MAP.getOrDefault(monthNumber, "неизвестный-месяц");

        System.out.println("Сегодня " + dayOutput + ", " + day + " " + monthOutput);
    }
}
