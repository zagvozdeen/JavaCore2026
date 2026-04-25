package lab6;

public class Example6 {
    public static void main(String[] args) throws InterruptedException {
        Thread evenThread = new Thread(() -> printNumbersByParity(2), "Четный поток");
        Thread oddThread = new Thread(() -> printNumbersByParity(1), "Нечетный поток");

        evenThread.start();
        oddThread.start();

        evenThread.join();
        oddThread.join();
    }

    private static void printNumbersByParity(int firstNumber) {
        for (int i = firstNumber; i <= 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
