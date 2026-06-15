import java.util.Scanner;

abstract class PaymentMethod {
    private String transactionId;

    public PaymentMethod(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract boolean validate(double amount);

    public abstract String processPayment(double amount);
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String transactionId, String cardNumber) {
        super(transactionId);
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean validate(double amount) {
        return cardNumber != null && cardNumber.length() == 16 && amount > 0;
    }

    @Override
    public String processPayment(double amount) {
        if (!validate(amount)) {
            return "FAILED";
        }
        return "SUCCESS";
    }
}

class UPIPayment extends PaymentMethod {
    private String upiId;

    public UPIPayment(String transactionId, String upiId) {
        super(transactionId);
        this.upiId = upiId;
    }

    @Override
    public boolean validate(double amount) {
        return upiId != null && upiId.contains("@") && amount > 0;
    }

    @Override
    public String processPayment(double amount) {
        if (!validate(amount)) {
            return "FAILED";
        }
        return "SUCCESS";
    }
}

class PaymentGateway {
    public void executeTransaction(PaymentMethod paymentMethod, double amount) {
        String status = paymentMethod.processPayment(amount);

        System.out.println("Transaction ID: " + paymentMethod.getTransactionId());
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);

        if (status.equals("SUCCESS")) {
            System.out.println("Transaction completed successfully");
        } else {
            System.out.println("Transaction validation failed");
        }
    }
}

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        sc.nextLine();

        String transactionId = sc.nextLine();
        double amount = sc.nextDouble();
        sc.nextLine();

        PaymentMethod paymentMethod;

        if (choice == 1) {
            String cardNumber = sc.nextLine();
            paymentMethod = new CreditCardPayment(transactionId, cardNumber);
        } else {
            String upiId = sc.nextLine();
            paymentMethod = new UPIPayment(transactionId, upiId);
        }

        PaymentGateway gateway = new PaymentGateway();
        gateway.executeTransaction(paymentMethod, amount);

        sc.close();
    }
}