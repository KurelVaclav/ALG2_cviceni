package tul.alg2.cv;

/**
 *
 * @author Václav Kurel
 */
public class Circle extends Shape{ //Circle je typově kompatibilní s Shape => Circle IS A Shape
//X Shape není kompatibilní se Circle - logicky jako FZ u databází 
    // data
    private double r; //poloměr

    //konstruktor - public změnit na private!!!
    private Circle(double r) {
        this.r = r;
    }
//    public Circle(double d){ //toto nelze!!!!!!
//        this.r = d/2;
//    }
    //nemohu použít dva konstruktory se stejným počtem parametrů, abych mohl měnit názvy- tovární metody
    //tovární metoda - if nemohu použít dva kontruktory, jakýkoliv název, často

    public static Circle getInstanceD(double d) {
        //zavolat konstruktor vždy
        return new Circle(d / 2);
    }

    public static Circle getInstanceR(double r) {
        //zavolat konstruktor vždy
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

    //metoda na výpočet obsahu, už ne static  - volám na objekt kruh
    //musím v potomkovy překrýt metodu
    @Override
    public double computeArea() {
        return Math.PI * r * r;
    }

}
