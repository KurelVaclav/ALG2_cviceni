package tul.alg2.cv;

/**
 *
 * @author Václav Kurel
 *
 */
public abstract class Shape implements Comparable<Shape> { //předek; abstraktní mohu dávat metody které nemají tělo 
    //data

    protected String jmeno = "Geometric object";

    //metody
    //zde dědičnost  - metoda public or protected - zdědí se do potomků :)
    //musím překrýt metodu u potomků @override
    public abstract double computeArea(); //musí být překrytá v potomcích

    public String getShapeName() { //jen přepoužitá v potomcích
        return this.getClass().getSimpleName(); //getClass vrátí objekt, getSimpleName vrátí String - vrátí "Circle"
    }

    @Override
    public String toString() { //překrytí metody toString třídy Object, defaultná implementace, která může být překrytá
        return jmeno + ": " + getShapeName();
    }

    @Override
    public int compareTo(Shape o) {
        if (this.computeArea() > o.computeArea()) {
            return -1;
        } else if (this.computeArea() < o.computeArea()) {
            return 1;
        }
        return 0;
    }

}
