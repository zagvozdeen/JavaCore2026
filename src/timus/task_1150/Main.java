package timus.task_1150;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);
        long n = in.nextLong();
        long[] count = new long[10];

        for (long factor = 1; factor <= n; factor *= 10) {
            long lower = n % factor;
            long current = (n / factor) % 10;
            long higher = n / (factor * 10);

            for (int digit = 1; digit <= 9; digit++) {
                if (current > digit) {
                    count[digit] += (higher + 1) * factor;
                } else if (current == digit) {
                    count[digit] += higher * factor + lower + 1;
                } else {
                    count[digit] += higher * factor;
                }
            }

            if (higher == 0) {
                continue;
            }

            count[0] += (higher - 1) * factor;
            if (current == 0) {
                count[0] += lower + 1;
            } else {
                count[0] += factor;
            }
        }

        StringBuilder out = new StringBuilder();
        for (long value : count) {
            out.append(value).append('\n');
        }
        System.out.print(out);
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr;
        private int len;

        private FastScanner(InputStream in) {
            this.in = new BufferedInputStream(in);
        }

        private long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;
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
