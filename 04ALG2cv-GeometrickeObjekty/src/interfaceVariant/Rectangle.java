package interfaceVariant;

/**
 *
 * @author Václav Kurel
 */
public class Rectangle implements ShapeInterface { // Rectangle je typově kompatibilní s ShapeInterface
    //data

    private double a;
    private double b;
    private double area;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
        this.area = area(); //v kontruktoru volat jen privátní metody!!
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    //vrátí obsah
    public double getArea() {
        return area;
    }

    @Override
    public double computeArea() {
        return area;
    }

    private double area() {
        return a * b;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "a=" + a + ", b=" + b + '}';
    }

}
