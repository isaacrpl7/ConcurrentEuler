package fixedthread;

public class Factorial {
    private Double result;
    public Factorial(int num) {
        Double factorial = 1.0;
        for(int i = 1; i <= num; ++i)
        {
            factorial = factorial * Double.valueOf(i);
        }
        this.result = factorial;
    }

    public Double getResult() {
        return this.result;
    }
}
