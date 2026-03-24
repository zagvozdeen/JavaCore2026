package timus.task_1084;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Locale;

public class Main {
    private static final double EPS = 1e-12;

    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);
        double side = in.nextInt();
        double rope = in.nextInt();

        double half = side / 2.0;
        double cornerRadius = Math.hypot(half, half);
        double area;

        if (rope <= half + EPS) {
            area = Math.PI * rope * rope;
        } else if (rope >= cornerRadius - EPS) {
            area = side * side;
        } else {
            double x = Math.sqrt(rope * rope - half * half);
            area = 4.0 * (half * x + integral(rope, half) - integral(rope, x));
        }

        PrintWriter out = new PrintWriter(System.out);
        out.printf(Locale.US, "%.3f%n", area);
        out.flush();
    }

    private static double integral(double radius, double x) {
        double y = Math.sqrt(Math.max(0.0, radius * radius - x * x));
        double angle = Math.asin(clamp(x / radius));
        return 0.5 * (x * y + radius * radius * angle);
    }

    private static double clamp(double value) {
        if (value < -1.0) {
            return -1.0;
        }
        if (value > 1.0) {
            return 1.0;
        }
        return value;
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr;
        private int len;

        private FastScanner(InputStream in) {
            this.in = new BufferedInputStream(in);
        }

        private int nextInt() throws IOException {
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

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }
    }
}
