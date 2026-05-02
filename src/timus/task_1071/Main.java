package timus.task_1071;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[] xDigits = new int[32];
        int[] yDigits = new int[32];

        for (int base = 2; base <= x; base++) {
            if (canEraseDigits(x, y, base, xDigits, yDigits)) {
                System.out.print(base);
                return;
            }
        }

        System.out.print("No solution");
    }

    private static boolean canEraseDigits(int x, int y, int base, int[] xDigits, int[] yDigits) {
        int xLength = fillDigits(x, base, xDigits);
        int yLength = fillDigits(y, base, yDigits);

        int yIndex = yLength - 1;
        for (int xIndex = xLength - 1; xIndex >= 0 && yIndex >= 0; xIndex--) {
            if (xDigits[xIndex] == yDigits[yIndex]) {
                yIndex--;
            }
        }

        return yIndex < 0;
    }

    private static int fillDigits(int number, int base, int[] digits) {
        int length = 0;

        while (number > 0) {
            digits[length++] = number % base;
            number /= base;
        }

        return length;
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

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }
    }
}
