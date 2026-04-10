package lab4;

public class Example6 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (ArithmeticException e) {
            System.out.println("1");
        } catch (RuntimeException e) {
            // Исправление: блок catch(RuntimeException)
            // перенесен выше catch(Exception), иначе пример не компилируется,
            // потому что предок не должен стоять раньше потомка.
            System.out.println("3");
        } catch (Exception e) {
            System.out.println("2");
        }
        System.out.println("4");
    }
}
