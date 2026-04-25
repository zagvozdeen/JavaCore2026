package timus.task_1079;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int[] queries = new int[10];
        int count = 0;
        int maxN = 0;

        while (true) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            }

            queries[count++] = n;
            if (n > maxN) {
                maxN = n;
            }
        }

        int[] maxValues = buildPrefixMaximums(maxN);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < count; i++) {
            out.append(maxValues[queries[i]]).append('\n');
        }
        System.out.print(out);
    }

    private static int[] buildPrefixMaximums(int maxN) {
        int[] values = new int[Math.max(2, maxN + 1)];
        int[] prefixMax = new int[Math.max(2, maxN + 1)];

        values[0] = 0;
        values[1] = 1;
        prefixMax[0] = 0;
        prefixMax[1] = 1;

        for (int i = 2; i <= maxN; i++) {
            if ((i & 1) == 0) {
                values[i] = values[i / 2];
            } else {
                values[i] = values[i / 2] + values[i / 2 + 1];
            }

            prefixMax[i] = Math.max(prefixMax[i - 1], values[i]);
        }

        return prefixMax;
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
