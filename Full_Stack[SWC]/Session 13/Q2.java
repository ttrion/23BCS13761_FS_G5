import java.util.*;
import java.util.stream.Collectors;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            numbers.add(sc.nextInt());
        }

        Set<Integer> seen = new HashSet<>();

        List<Integer> duplicates = numbers.stream()
                .filter(num -> !seen.add(num))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Duplicate Elements:");

        if (duplicates.isEmpty()) {
            System.out.println("No Duplicates Found");
        } else {
            duplicates.forEach(System.out::println);
        }

        sc.close();
    }
}