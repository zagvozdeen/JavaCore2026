package timus.task_1070;

public class Main {
    private static final int DAY = 24 * 60;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int departureThere = parseTime(scanner.next());
        int arrivalThere = parseTime(scanner.next());
        int departureBack = parseTime(scanner.next());
        int arrivalBack = parseTime(scanner.next());

        for (int hourOffset = -5; hourOffset <= 5; hourOffset++) {
            int offset = hourOffset * 60;

            int durationThere = normalize(arrivalThere - offset - departureThere);
            int durationBack = normalize(arrivalBack - departureBack + offset);

            if (durationThere <= 360
                    && durationBack <= 360
                    && Math.abs(durationThere - durationBack) <= 10) {
                System.out.print(Math.abs(hourOffset));
                return;
            }
        }
    }

    private static int parseTime(String time) {
        int dotIndex = time.indexOf('.');
        int hours = Integer.parseInt(time.substring(0, dotIndex));
        int minutes = Integer.parseInt(time.substring(dotIndex + 1));
        return hours * 60 + minutes;
    }

    private static int normalize(int minutes) {
        minutes %= DAY;
        if (minutes < 0) {
            minutes += DAY;
        }
        return minutes;
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

            StringBuilder value = new StringBuilder();
            while (c > ' ') {
                value.append((char) c);
                c = read();
            }
            return value.toString();
        }
    }
}
