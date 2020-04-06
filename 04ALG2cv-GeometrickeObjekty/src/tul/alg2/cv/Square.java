package tul.alg2.cv;

/**
 *
 * @author Václav Kurel
 */
public class Square extends Rectangle {

    double a;

    public Square(double a) {
        super(a, a);
        this.a = a;
    }

    @Override
    public String toString() {
        return "Square{" + "a=" + a + '}';
    }

}
