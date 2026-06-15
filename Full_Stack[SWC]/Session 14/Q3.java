import java.util.ArrayList;
import java.util.List;

public class Q3 {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 10_000_000; i++) {
            numbers.add(i);
        }

        long startSequential = System.nanoTime();

        long sequentialSum = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();

        long endSequential = System.nanoTime();

        long startParallel = System.nanoTime();

        long parallelSum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();

        long endParallel = System.nanoTime();

        long sequentialTime = endSequential - startSequential;
        long parallelTime = endParallel - startParallel;

        System.out.println("Sequential Stream Sum: " + sequentialSum);
        System.out.println("Sequential Execution Time: " + sequentialTime + " ns");

        System.out.println();

        System.out.println("Parallel Stream Sum: " + parallelSum);
        System.out.println("Parallel Execution Time: " + parallelTime + " ns");

        System.out.println();

        if (parallelTime < sequentialTime) {
            System.out.println("Parallel Stream was faster by " +
                    (sequentialTime - parallelTime) + " ns");
        } else if (sequentialTime < parallelTime) {
            System.out.println("Sequential Stream was faster by " +
                    (parallelTime - sequentialTime) + " ns");
        } else {
            System.out.println("Both approaches took the same time");
        }

        System.out.println();
        System.out.println("Performance Analysis:");
        System.out.println("Sequential streams process data using a single thread.");
        System.out.println("Parallel streams divide work across multiple CPU cores.");
        System.out.println("Parallel streams may improve performance for large datasets and CPU-intensive operations.");
        System.out.println("For small datasets, parallel streams can be slower due to thread management overhead.");
    }
}