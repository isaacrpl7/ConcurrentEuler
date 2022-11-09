package fixedthread;

import java.util.List;

public class SumAggregator implements Runnable {

    private List<Double> terms;

    SumAggregator(List<Double> terms) {
        this.terms = terms;
    }
    @Override
    public void run() {
        double soma = 0.0;
        for (int i = 0; i < terms.size(); i++) {
            soma += terms.get(i);
        }
        System.out.println(soma);
    }
}
