import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries =
                new ArrayList<>(frequencyMap.entrySet());

        entries.sort((e1, e2) -> {
            int freqCompare = Integer.compare(e2.getValue(), e1.getValue());
            if (freqCompare == 0) {
                return Integer.compare(e1.getKey(), e2.getKey());
            }
            return freqCompare;
        });

        System.out.println("Element\tFrequency");

        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        if (!entries.isEmpty()) {
            System.out.println("Top Frequent Element: " +
                    entries.get(0).getKey() +
                    " Frequency: " +
                    entries.get(0).getValue());
        }

        sc.close();
    }
}