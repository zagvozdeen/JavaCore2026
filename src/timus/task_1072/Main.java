package timus.task_1072;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        List<Interface>[] computers = new List[n];

        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            computers[i] = new ArrayList<>(k);
            for (int j = 0; j < k; j++) {
                int ip = parseAddress(scanner.next());
                int mask = parseAddress(scanner.next());
                computers[i].add(new Interface(ip, mask));
            }
        }

        int start = scanner.nextInt() - 1;
        int finish = scanner.nextInt() - 1;

        boolean[][] graph = buildGraph(computers);
        int[] parent = bfs(graph, start, finish);

        if (parent[finish] == -1) {
            System.out.print("No");
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int current = finish; current != start; current = parent[current]) {
            path.add(current + 1);
        }
        path.add(start + 1);
        Collections.reverse(path);

        StringBuilder answer = new StringBuilder("Yes\n");
        for (int computer : path) {
            answer.append(computer).append(' ');
        }
        System.out.print(answer.toString().trim());
    }

    private static boolean[][] buildGraph(List<Interface>[] computers) {
        int n = computers.length;
        boolean[][] graph = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sameSubnet(computers[i], computers[j])) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }

        return graph;
    }

    private static boolean sameSubnet(List<Interface> first, List<Interface> second) {
        for (Interface left : first) {
            for (Interface right : second) {
                if ((left.ip & left.mask) == (right.ip & right.mask)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int[] bfs(boolean[][] graph, int start, int finish) {
        int[] parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        parent[start] = start;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == finish) {
                break;
            }

            for (int next = 0; next < graph.length; next++) {
                if (graph[current][next] && parent[next] == -1) {
                    parent[next] = current;
                    queue.add(next);
                }
            }
        }

        return parent;
    }

    private static int parseAddress(String address) {
        String[] parts = address.split("\\.");
        int result = 0;
        for (String part : parts) {
            result = (result << 8) | Integer.parseInt(part);
        }
        return result;
    }

    private static final class Interface {
        private final int ip;
        private final int mask;

        private Interface(int ip, int mask) {
            this.ip = ip;
            this.mask = mask;
        }
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr;
        private int len;

        private FastScanner(InputStream in) {
            this.in = new BufferedInputStream(in);
        }

        private int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        private String next() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            StringBuilder result = new StringBuilder();
            while (c > ' ') {
                result.append((char) c);
                c = read();
            }
            return result.toString();
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }
    }
}
