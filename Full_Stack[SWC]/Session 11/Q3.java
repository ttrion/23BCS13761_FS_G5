import java.util.concurrent.atomic.AtomicInteger;

class UnsafeCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SafeCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

public class Q3 {
    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        SafeCounter safeCounter = new SafeCounter();

        int threadCount = 10;
        int incrementsPerThread = 10000;

        Thread[] unsafeThreads = new Thread[threadCount];
        Thread[] safeThreads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCounter.increment();
                }
            });

            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    safeCounter.increment();
                }
            });
        }

        for (int i = 0; i < threadCount; i++) {
            unsafeThreads[i].start();
            safeThreads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            unsafeThreads[i].join();
            safeThreads[i].join();
        }

        System.out.println("Expected Count: " + (threadCount * incrementsPerThread));
        System.out.println("Unsafe Counter Result: " + unsafeCounter.getCount());
        System.out.println("Safe Counter Result: " + safeCounter.getCount());
    }
}