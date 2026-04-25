package lab6;

public class Example5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        thread.start();
        thread.join();
    }
}
