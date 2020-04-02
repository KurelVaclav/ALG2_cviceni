package tul.alg2.cv;

/**
 *
 * @author Václav Kurel
 */
public abstract class Shape { //abstraktní mohu dávat metody které nemají tělo 

    //zde dědičnost  - metoda public or protected - zdědí se do potomků :)
    //musím překrýt metodu u potomků @override
    public abstract double computeArea();

}
