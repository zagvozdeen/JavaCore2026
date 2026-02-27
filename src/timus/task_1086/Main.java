public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int k = scanner.nextInt();

        int[] queries = new int[k];
        int maxN = 0;
        for (int i = 0; i < k; i++) {
            queries[i] = scanner.nextInt();
            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        int[] primes = buildPrimes(maxN);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < k; i++) {
            out.append(primes[queries[i]]).append('\n');
        }
        System.out.print(out);
    }

    private static int[] buildPrimes(int n) {
        if (n <= 0) {
            return new int[1];
        }

        int limit = estimateUpperBound(n);
        while (true) {
            boolean[] composite = new boolean[limit + 1];
            int[] primes = new int[n + 1];
            int count = 0;

            for (int i = 2; i <= limit; i++) {
                if (!composite[i]) {
                    count++;
                    if (count <= n) {
                        primes[count] = i;
                    }
                    if ((long) i * i <= limit) {
                        for (int j = i * i; j <= limit; j += i) {
                            composite[j] = true;
                        }
                    }
                }
            }

            if (count >= n) {
                return primes;
            }
            limit *= 2;
        }
    }

    private static int estimateUpperBound(int n) {
        if (n < 6) {
            return 15;
        }
        double dn = n;
        return (int) Math.ceil(dn * (Math.log(dn) + Math.log(Math.log(dn)))) + 10;
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
