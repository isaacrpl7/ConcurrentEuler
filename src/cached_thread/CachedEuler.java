package cached_thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;

public class CachedEuler {
    public static void main(String[] args){
        int total_of_terms = Integer.parseInt(args[0]);
        final ThreadGroup threadGroup = new ThreadGroup("workers");
        List<Double> terms = Collections.synchronizedList(new ArrayList<Double>());
        SumAggregator aggregator = new SumAggregator(terms);
        //CyclicBarrier barrier = new CyclicBarrier(6, aggregator);

        ExecutorService executor = Executors
                .newCachedThreadPool(new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        return new Thread(threadGroup, r);
                    }
                });

        for (int i = 0; i < total_of_terms; i++) {
            int start_position = i;
            int end_position = i + 1;

            Calculate thread = new Calculate(start_position, end_position, terms);

            executor.submit(thread);
        }

        System.out.println("Number of Threads: " + (threadGroup.activeCount()));
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Tasks were interrupted.");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Tasks will be interrupted.");
            }

            executor.shutdownNow();
            System.out.println("Executor has been finished.");
            aggregator.run();
        }

    }
}
