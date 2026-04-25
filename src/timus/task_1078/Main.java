package timus.task_1078;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();

        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }
            segments[i] = new Segment(left, right, i + 1);
        }

        Arrays.sort(segments, (a, b) -> {
            int lengthCompare = Integer.compare(a.length(), b.length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }

            int leftCompare = Integer.compare(b.left, a.left);
            if (leftCompare != 0) {
                return leftCompare;
            }

            return Integer.compare(a.right, b.right);
        });

        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int bestLength = 0;
        int bestIndex = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (isInside(segments[j], segments[i]) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > bestLength) {
                bestLength = dp[i];
                bestIndex = i;
            }
        }

        int[] answer = new int[bestLength];
        int position = bestLength - 1;
        int current = bestIndex;
        while (current != -1) {
            answer[position--] = segments[current].index;
            current = parent[current];
        }

        StringBuilder out = new StringBuilder();
        out.append(bestLength).append('\n');
        for (int i = 0; i < answer.length; i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(answer[i]);
        }

        System.out.print(out);
    }

    private static boolean isInside(Segment inner, Segment outer) {
        return inner.left > outer.left && inner.right < outer.right;
    }

    private static class Segment {
        private final int left;
        private final int right;
        private final int index;

        private Segment(int left, int right, int index) {
            this.left = left;
            this.right = right;
            this.index = index;
        }

        private int length() {
            return right - left;
        }
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
