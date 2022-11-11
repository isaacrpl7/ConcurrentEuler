package workstealingthreads;

import fixedthread.Factorial;

import java.util.List;

public class Calculate implements Runnable{
    private int nTermStart;
    private int nTermEnd;
    //private CyclicBarrier barrier;
    private List<Double> terms;

    public Calculate(int nTermStart, int nTermEnd, List<Double> terms) {
        this.nTermStart = nTermStart;
        this.nTermEnd = nTermEnd;
        //this.barrier = barrier;
        this.terms = terms;
    }

    @Override
    public void run() {
        double localSum = 0;
        for(int i = nTermStart; i < nTermEnd; i++) {
            Factorial fact = new Factorial(i);
            localSum += 1 / fact.getResult();
            System.out.println("Soma: "+fact.getResult() + " Iteracao: " + i + " Thread: " + Thread.currentThread().getName());
        }
        this.terms.add(localSum);

//        try {
//            barrier.await();
//        } catch (InterruptedException | BrokenBarrierException e) {
//            e.printStackTrace();
//        }
    }
}
