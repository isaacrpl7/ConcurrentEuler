package fixedthread;

public class Factorial {
    private Double result;
    Factorial(int num) {
        Double factorial = 1.0;
        for(int i = 1; i <= num; ++i)
        {
            factorial = factorial * Double.valueOf(i);
        }
        this.result = factorial;
    }

    Double getResult() {
        return this.result;
    }
}
