package timus.task_1011;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int p = parsePercent(scanner.next());
        int q = parsePercent(scanner.next());

        long citizens = 1L;
        while (true) {
            long minConductors = (long) p * citizens / 10000L + 1L;
            long maxConductors = ((long) q * citizens - 1L) / 10000L;

            if (minConductors <= maxConductors) {
                System.out.print(citizens);
                return;
            }

            citizens++;
        }
    }

    private static int parsePercent(String value) {
        int dotIndex = value.indexOf('.');
        if (dotIndex == -1) {
            return Integer.parseInt(value) * 100;
        }

        String integerPart = value.substring(0, dotIndex);
        String fractionPart = value.substring(dotIndex + 1);
        if (fractionPart.length() == 1) {
            fractionPart += "0";
        } else if (fractionPart.length() > 2) {
            fractionPart = fractionPart.substring(0, 2);
        }

        return Integer.parseInt(integerPart) * 100 + Integer.parseInt(fractionPart);
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

        String next() throws Exception {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            if (c == -1) {
                return null;
            }

            StringBuilder token = new StringBuilder();
            while (c > ' ') {
                token.append((char) c);
                c = read();
            }

            return token.toString();
        }
    }
}
