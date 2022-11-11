package cached_thread;

import java.util.List;

public class SumAggregator {
    private List<Double> terms;

    SumAggregator(List<Double> terms) {
        this.terms = terms;
    }

    public void run() {
        double soma = 0.0;
        for (int i = 0; i < terms.size(); i++) {
            soma += terms.get(i);
        }
        System.out.println("Aproximacao: "+soma);
    }
}
