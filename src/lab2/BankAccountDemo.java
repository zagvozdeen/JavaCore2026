package lab2;

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount firstAccount = new BankAccount("ACC-001", "Иван Иванов", 1000);
        firstAccount.deposit(500);
        firstAccount.withdraw(300);

        BankAccount secondAccount = new BankAccount();
        secondAccount.createAccount("ACC-002", "Мария Петрова", 2500);
        secondAccount.withdraw(400);
        secondAccount.deposit(150);

        printAccountInfo(firstAccount);
        printAccountInfo(secondAccount);
    }

    private static void printAccountInfo(BankAccount account) {
        System.out.println("Счет: " + account.getAccountNumber());
        System.out.println("Владелец: " + account.getOwnerName());
        System.out.println("Текущий баланс: " + account.getBalance());
        System.out.println();
    }
}
