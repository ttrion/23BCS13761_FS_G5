import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fileName = sc.nextLine();

        int validCount = 0;
        int invalidCount = 0;
        long sum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                try {
                    line = line.trim();

                    if (line.isEmpty()) {
                        throw new NumberFormatException();
                    }

                    int number = Integer.parseInt(line);
                    sum += number;
                    validCount++;

                } catch (NumberFormatException e) {
                    invalidCount++;
                }
            }

            System.out.println("Sum of Valid Integers: " + sum);
            System.out.println("Total Valid Numbers: " + validCount);
            System.out.println("Total Invalid Lines: " + invalidCount);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        sc.close();
    }
}