package tul.alg2.cv;

/**
 *
 * @author MP
 */
public class Triangle extends Shape {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }

    @Override
    public double computeArea() { //Heronův vzorec
        double t = (a + b + c) / 2;
        return Math.sqrt(t * (t - a) * (t - b) * (t - c));
    }

}
