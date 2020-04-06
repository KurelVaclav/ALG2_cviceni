package TUL.ALG2;

/**
 *
 * @author Václav Kurel
 */
public class Account {

    //data
    private double sum;

    //konstruktor
    public Account(double sum) {
        this.sum = sum;
    }

    public Account() {

    }

    //metody
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Dostupný zůstatek: " + sum + " Kč";
    }

    public void cashIn(double sum) {
        if (sum < 0) {
            throw new IllegalArgumentException("Zadejte kladnou částku");
        }
        this.sum += sum;
    }

    public void cashOut(double sum) {
        if (sum > this.sum) {
            throw new IllegalArgumentException("Nedostatek peněz na účtě, zvolte max" + this.sum);
        } else {
            this.sum -= sum;
        }

    }

}
