import java.util.*;
import java.util.stream.Collectors;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            words.add(sc.nextLine());
        }

        Map<Integer, List<String>> groupedWords = words.stream()
                .collect(Collectors.groupingBy(String::length));

        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        System.out.println("Grouped Words:");

        for (Map.Entry<Integer, List<String>> entry : groupedWords.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Word Occurrences:");

        for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        sc.close();
    }
}