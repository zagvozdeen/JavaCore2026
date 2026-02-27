import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int result = 2 * n * a * b;
        System.out.print(result);
    }

    private static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);

        int nextInt() throws IOException {
            int c;
            do {
                c = in.read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = in.read();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = in.read();
            }

            return value * sign;
        }
    }
}
