package lab2;

public interface AccountOperations {
    void createAccount(String accountNumber, String ownerName, double initialBalance);

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();
}
