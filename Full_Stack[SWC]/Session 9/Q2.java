import java.util.Scanner;

abstract class Payment {
    public abstract void processPayment(double amount);
}

class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        if (cardNumber == null || cardNumber.length() != 16) {
            System.out.println("Invalid credit card number");
            return;
        }
        System.out.println("Credit Card Payment of " + amount + " processed successfully");
    }
}

class UPIPayment extends Payment {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void processPayment(double amount) {
        if (upiId == null || !upiId.matches("[a-zA-Z0-9._-]+@[a-zA-Z]+")) {
            System.out.println("Invalid UPI ID");
            return;
        }
        System.out.println("UPI Payment of " + amount + " processed successfully");
    }
}

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        double amount = sc.nextDouble();
        sc.nextLine();

        Payment payment;

        if (choice == 1) {
            String cardNumber = sc.nextLine();
            payment = new CreditCardPayment(cardNumber);
        } else if (choice == 2) {
            String upiId = sc.nextLine();
            payment = new UPIPayment(upiId);
        } else {
            System.out.println("Invalid payment method");
            return;
        }

        payment.processPayment(amount);

        sc.close();
    }
}