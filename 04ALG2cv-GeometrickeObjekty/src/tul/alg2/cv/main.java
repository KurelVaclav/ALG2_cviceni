package tul.alg2.cv;

import java.util.ArrayList;

/**
 *
 * @author Václav Kurel
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Circle c1 = Circle.getInstanceR(4);
//        System.out.println(c1);
//        System.out.println("Obsah kruhu = " + c1.computeArea());
        Circle c1 = Circle.getInstanceD(8);
        Rectangle r1 = new Rectangle(2, 3);
        Circle c2 = Circle.getInstanceR(2.6);

        System.out.println("1. varianta");
        double allArea1 = c1.computeArea() + r1.getArea() + c2.computeArea(); //zde bylo r1.getArea()
        System.out.println("plocha = " + allArea1);

        System.out.println("");
        System.out.println("2. varianta"); //dynamické pole - upravení dat přes metody
        ArrayList shapes2 = new ArrayList(); //dynamické pole objektů typu Object (nejvyšší DT v javě)
        //může obsahovat cokoliv, co je typově kompatibilní s Object (to je všechno)!!!!!!
        //naplním arrayList pomocí metod
        shapes2.add(c1); //uložili jsme do proměnné typu Object => alist vrátí Object => přetypovat při výpočtu
        shapes2.add(r1);
        shapes2.add(Circle.getInstanceR(2.6));
        double allArea2 = 0;
        //metoda procházení going through using index
        for (int i = 0; i < shapes2.size(); i++) { // metoda size NE length
//přetypovat na stejný DT Object + přes if rozlišit typ objektu            
            if (shapes2.get(i) instanceof Circle) {
                allArea2 += ((Circle) shapes2.get(i)).computeArea();
            } else if (shapes2.get(i) instanceof Rectangle) {
                allArea2 += ((Rectangle) shapes2.get(i)).computeArea();
            }
        }
        System.out.println("plocha var 2 = " + allArea2);
        //dědičnost+polymorfismus - říká vytvoř novou třídu shape nad kruh a obdélník - vede na 3. variantu
        System.out.println("");
        System.out.println("3. varianta");
        ArrayList<Shape> shapes3 = new ArrayList<>(); //dynamické pole objektů typu Shape
        //může obsahovat cokoliv co je typově kompatibilní s Shape (to je co jsou potomkové Shapu: Circle+Rectangel)!!!!
        //=>dát Circle,Rectanhle extends Shape; 
        //ukázka typové kompatibility 
//        Shape s = new Rectangle(5, 6);
//        Rectangle r = new Shape();
        //data
        shapes3.add(c1);
        shapes3.add(r1);
        shapes3.add(Circle.getInstanceR(2.6));
        double allArea3 = 0;
        //metoda for - each
        for (Shape shape : shapes3) {
            allArea3 += shape.computeArea(); //polymorfismus = až při běhu programu dle typu objektů se určuje co se použije za kod
            //computeArea() je mnohotvárná - dle okolností ukazuje na potřebný kód
            // tento = dynamický polymorfismus = vyhodnotí se metoda až při běhu programu VS
            // statický polymorfismus = "přetížení metody"
        }
        System.out.println("plocha 3 = " + allArea3);

    }

}
