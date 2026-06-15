import java.io.*;
import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        int processed = 0;
        int failed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    if (line.trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    System.out.println("Processed: " + line);
                    processed++;
                } catch (Exception e) {
                    failed++;
                    System.out.println("Skipped Invalid Line: " + line);
                }
            }

            System.out.println("Processed Records: " + processed);
            System.out.println("Failed Records: " + failed);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        sc.close();
    }
}