package timus.task_1025;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int k = scanner.nextInt();
        int[] groups = new int[k];

        for (int i = 0; i < k; i++) {
            groups[i] = scanner.nextInt();
        }

        Arrays.sort(groups);

        int groupsNeeded = k / 2 + 1;
        int supporters = 0;
        for (int i = 0; i < groupsNeeded; i++) {
            supporters += groups[i] / 2 + 1;
        }

        System.out.print(supporters);
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
