package lab3;

public class Example5 {
    private static int level = 0;

    public static void main(String[] args) {
        int result = fact(5);
        System.out.println("Результат: " + result);
    }

    public static int fact(int n) {
        printSpace();
        System.out.println("Вход в fact(" + n + ")");
        level++;

        int result;

        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = fact(n - 2) + fact(n - 1);
        }

        level--;
        printSpace();
        System.out.println("Выход из fact(" + n + ") = " + result);

        return result;
    }

    public static void printSpace() {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }
}
