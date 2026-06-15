import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private String pin;
    private double balance;
    private List<String> transactions;

    public Account(String pin, double balance) {
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            transactions.add("Failed Withdrawal: " + amount);
            return false;
        }
        balance -= amount;
        transactions.add("Withdrawal: " + amount);
        return true;
    }

    public void printTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No Transactions");
            return;
        }

        System.out.println("Transaction Records:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account account = new Account("1234", 10000);

        String enteredPin = sc.nextLine();

        if (!account.authenticate(enteredPin)) {
            System.out.println("Authentication Failed");
            return;
        }

        System.out.println("Authentication Successful");

        while (true) {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + account.getBalance());
                    break;

                case 2:
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit Successful");
                    break;

                case 3:
                    double withdrawAmount = sc.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal Successful");
                    } else {
                        System.out.println("Insufficient Balance");
                    }
                    break;

                case 4:
                    account.printTransactions();
                    break;

                case 5:
                    System.out.println("Thank You");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}