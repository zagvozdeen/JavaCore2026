package lab6;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Example4 {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        long finishTime = System.currentTimeMillis() + 10_000;

        Thread firstThread = new Thread(createTask(finishTime), "Поток-1");
        Thread secondThread = new Thread(createTask(finishTime), "Поток-2");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }

    private static Runnable createTask(long finishTime) {
        return () -> {
            while (System.currentTimeMillis() < finishTime) {
                System.out.println(Thread.currentThread().getName() + ": "
                        + LocalTime.now().format(TIME_FORMATTER));

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };
    }
}
