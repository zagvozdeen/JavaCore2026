package timus.task_1010;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();

        long previous = scanner.nextLong();
        long bestDiff = Long.MIN_VALUE;
        int bestLeft = 1;

        for (int index = 2; index <= n; index++) {
            long current = scanner.nextLong();
            long diff = Math.abs(current - previous);

            if (diff > bestDiff) {
                bestDiff = diff;
                bestLeft = index - 1;
            }

            previous = current;
        }

        System.out.print(bestLeft + " " + (bestLeft + 1));
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
            return (int) nextLong();
        }

        long nextLong() throws Exception {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0L;
            while (c > ' ') {
                value = value * 10L + (c - '0');
                c = read();
            }

            return value * sign;
        }
    }
}
