package lab2;

public class BankAccount implements AccountOperations {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        createAccount(accountNumber, ownerName, initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void createAccount(String accountNumber, String ownerName, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма пополнения должна быть больше нуля");
        }

        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть больше нуля");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }

        balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
