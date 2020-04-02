package tul.alg2.cv;

/**
 * immutable - neměnný objekt 
 * @author Václav Kurel
 */
public class Rectangle extends Shape{ // Rectangle je typově kompatibilní s Shape, obráceně ne logicky
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
    
    //přepsat metodu v potomkovy
    @Override
    public double computeArea(){ //pouze vracím hodnotu
        return area;
    }

    //výpočet obsahu - nechceme 5x počítat obsah => do konstruktoru
    private double area(){
        return a*b;
    }
    
    @Override
    public String toString() {
        return "Rectangle{" + "a=" + a + ", b=" + b + '}';
    }
    
    
}
