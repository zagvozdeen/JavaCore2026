package timus.task_1112;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        Arrays.sort(segments, Comparator
                .comparingInt((Segment s) -> s.right)
                .thenComparingInt(s -> s.left));

        List<Segment> selected = new ArrayList<>();
        int lastRight = Integer.MIN_VALUE;

        for (Segment segment : segments) {
            if (segment.left >= lastRight) {
                selected.add(segment);
                lastRight = segment.right;
            }
        }

        selected.sort(Comparator
                .comparingInt((Segment s) -> s.left)
                .thenComparingInt(s -> s.right));

        StringBuilder out = new StringBuilder();
        out.append(selected.size()).append('\n');
        for (Segment segment : selected) {
            out.append(segment.left).append(' ').append(segment.right).append('\n');
        }
        System.out.print(out);
    }

    private static class Segment {
        private final int left;
        private final int right;

        private Segment(int left, int right) {
            this.left = left;
            this.right = right;
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
