import java.util.*;
import java.util.concurrent.*;

public class Q2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<String>> results = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            Callable<String> task = () -> {
                Thread.sleep(1000);
                return "Task " + taskId + " completed by " + Thread.currentThread().getName();
            };

            results.add(executor.submit(task));
        }

        for (Future<String> future : results) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Executor shutdown completed");
    }
}