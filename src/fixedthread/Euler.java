package fixedthread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Euler {
    public static void main(String[] args){
        int total_of_terms = Integer.parseInt(args[0]);
        int number_of_threads = Integer.parseInt(args[1]);


        List<Double> terms = Collections.synchronizedList(new ArrayList<Double>());
        SumAggregator aggregator = new SumAggregator(terms);
        CyclicBarrier barrier = new CyclicBarrier(number_of_threads, aggregator);

        ExecutorService executor = Executors.newFixedThreadPool(number_of_threads);

        for (int i = 0; i < number_of_threads; i++) {
            int start_position = (total_of_terms/number_of_threads) * i;
            int position_overflow = (start_position + (total_of_terms/number_of_threads)) - total_of_terms;
            int end_position;

            if(position_overflow > 0) {
                end_position = (start_position + (total_of_terms/number_of_threads)) - position_overflow;
            } else {
                end_position = start_position + (total_of_terms/number_of_threads);
            }

            Calculate thread = new Calculate(start_position, end_position, barrier, terms);


            executor.submit(thread);
        }

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
        }

    }
}
