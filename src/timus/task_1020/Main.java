package timus.task_1020;

import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);

        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        double r = scanner.nextDouble();

        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            y[i] = scanner.nextDouble();
        }

        double perimeter = 0.0;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            double dx = x[i] - x[next];
            double dy = y[i] - y[next];
            perimeter += Math.hypot(dx, dy);
        }

        double ropeLength = perimeter + 2.0 * Math.PI * r;
        System.out.printf("%.2f", ropeLength);
    }

    private static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws Exception {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws Exception {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        double nextDouble() throws Exception {
            return Double.parseDouble(nextToken());
        }

        String nextToken() throws Exception {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            StringBuilder token = new StringBuilder();
            while (c > ' ') {
                token.append((char) c);
                c = read();
            }
            return token.toString();
        }
    }
}
