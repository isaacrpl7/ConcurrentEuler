package fixedthread;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Calculate implements Runnable {
    private int nTermStart;
    private int nTermEnd;
    private CyclicBarrier barrier;
    private List<Double> terms;

    public Calculate(int nTermStart, int nTermEnd, CyclicBarrier barrier, List<Double> terms) {
        this.nTermStart = nTermStart;
        this.nTermEnd = nTermEnd;
        this.barrier = barrier;
        this.terms = terms;
    }

    @Override
    public void run() {
        double localSum = 0;
        for(int i = nTermStart; i < nTermEnd; i++) {
            Factorial fact = new Factorial(i);
            localSum += 1 / fact.getResult();
            System.out.println(fact.getResult() + " " + i);
        }
        this.terms.add(localSum);

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
