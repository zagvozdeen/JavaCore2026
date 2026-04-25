package lab6;

public class Example3 {
    private static final Object lock = new Object();
    private static int number = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread evenThread = new Thread(() -> {
            synchronized (lock) {
                while (number <= 10) {
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                lock.notifyAll();
            }
        });

        Thread oddThread = new Thread(() -> {
            synchronized (lock) {
                while (number <= 10) {
                    if (number % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                        number++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                lock.notifyAll();
            }
        });

        evenThread.setName("Even Thread");
        oddThread.setName("Odd Thread");

        evenThread.start();
        oddThread.start();

        evenThread.join();
        oddThread.join();
    }
}
