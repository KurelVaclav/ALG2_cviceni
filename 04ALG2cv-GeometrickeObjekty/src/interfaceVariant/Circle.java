package interfaceVariant;

/**
 *
 * @author Václav Kurel
 */
public class Circle implements ShapeInterface { //Circle je typově kompatibilní s ShapeInterface 
    // data

    private double r; //poloměr

    private Circle(double r) {
        this.r = r;
    }

    public static Circle getInstanceD(double d) {
        return new Circle(d / 2);
    }

    public static Circle getInstanceR(double r) {
        return new Circle(r);
    }

    //getter
    public double getR() {
        return r;
    }

    //toString
    @Override
    public String toString() {
        return "Circle{" + "r=" + r + '}';
    }

    @Override
    public double computeArea() {
        return Math.PI * r * r;
    }

}
