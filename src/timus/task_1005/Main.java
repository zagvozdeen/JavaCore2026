public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] w = new int[n];

        int total = 0;
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            total += w[i];
        }

        int best = Integer.MAX_VALUE;
        int masks = 1 << n;
        for (int mask = 0; mask < masks; mask++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += w[i];
                }
            }
            int diff = Math.abs(total - 2 * sum);
            if (diff < best) {
                best = diff;
            }
        }

        System.out.print(best);
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
    }
}
