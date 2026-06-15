import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            try {
                String input = sc.nextLine();
                int number = Integer.parseInt(input);
                System.out.println("Parsed Number: " + number);
            } catch (NumberFormatException e) {
                System.out.println("Parsing Error: Invalid number format");
            }

            try {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int result = a / b;
                System.out.println("Calculation Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Calculation Error: Division by zero");
            }

            try {
                sc.nextLine();
                String record = sc.nextLine();

                if (record.trim().isEmpty()) {
                    throw new Exception("Database record not found");
                }

                System.out.println("Database Record Processed: " + record);
            } catch (Exception e) {
                System.out.println("Database Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }

        System.out.println("Execution Completed");

        sc.close();
    }
}