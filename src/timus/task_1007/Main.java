package timus.task_1007;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int mod = n + 1;
        StringBuilder out = new StringBuilder();

        String word;
        while ((word = scanner.next()) != null) {
            out.append(restore(word, n, mod)).append('\n');
        }

        if (out.length() > 0) {
            out.setLength(out.length() - 1);
        }
        System.out.print(out);
    }

    private static String restore(String word, int n, int mod) {
        int length = word.length();

        if (length == n) {
            int checksum = weightedSum(word) % mod;
            if (checksum == 0) {
                return word;
            }

            char[] chars = word.toCharArray();
            chars[checksum - 1] = '0';
            return new String(chars);
        }

        if (length == n - 1) {
            return restoreAfterDeletion(word, n, mod);
        }

        return restoreAfterInsertion(word, mod);
    }

    private static String restoreAfterDeletion(String word, int n, int mod) {
        int length = word.length();
        int sum = weightedSum(word);
        int[] suffixOnes = new int[length + 2];

        for (int i = length; i >= 1; i--) {
            suffixOnes[i] = suffixOnes[i + 1] + (word.charAt(i - 1) - '0');
        }

        for (int position = 1; position <= n; position++) {
            int shiftedOnes = suffixOnes[position];

            if ((sum + shiftedOnes) % mod == 0) {
                return word.substring(0, position - 1) + '0' + word.substring(position - 1);
            }

            if ((sum + shiftedOnes + position) % mod == 0) {
                return word.substring(0, position - 1) + '1' + word.substring(position - 1);
            }
        }

        return word;
    }

    private static String restoreAfterInsertion(String word, int mod) {
        int length = word.length();
        int sum = weightedSum(word);
        int[] suffixOnes = new int[length + 2];

        for (int i = length; i >= 1; i--) {
            suffixOnes[i] = suffixOnes[i + 1] + (word.charAt(i - 1) - '0');
        }

        for (int position = 1; position <= length; position++) {
            int bit = word.charAt(position - 1) - '0';
            int restoredSum = sum - bit * position - suffixOnes[position + 1];
            if (restoredSum % mod == 0) {
                return word.substring(0, position - 1) + word.substring(position);
            }
        }

        return word;
    }

    private static int weightedSum(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '1') {
                sum += i + 1;
            }
        }
        return sum;
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

        String next() throws Exception {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            if (c == -1) {
                return null;
            }

            StringBuilder value = new StringBuilder();
            while (c > ' ') {
                value.append((char) c);
                c = read();
            }
            return value.toString();
        }
    }
}
