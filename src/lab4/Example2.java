package lab4;

public class Example2 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new RuntimeException("Непроверяемая ошибка");
            // Исправление: удален недостижимый оператор
            // System.out.println("1"); после throw, иначе пример не компилируется.
        } catch (Exception e) {
            System.out.println("2 " + e);
        }
        System.out.println("3");
    }
}
