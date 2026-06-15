import java.util.Scanner;

public class Q2 {

    public static void validateAge(int age) {
        if (age <= 18) {
            throw new IllegalArgumentException("Age must be greater than 18");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                int age = sc.nextInt();
                sc.nextLine();
                String email = sc.nextLine();

                validateAge(age);
                validateEmail(email);

                System.out.println("Registration Successful");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again");
            }
        }

        sc.close();
    }
}