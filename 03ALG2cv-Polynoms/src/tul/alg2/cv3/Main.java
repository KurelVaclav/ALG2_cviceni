package tul.alg2.cv3;

/**
 *
 * @author MP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] a = {6, 0, 3, 5};
        Polynom pol = Polynom.getInstanceReverted(a);
        System.out.println(pol);
        System.out.println("koeficient x^3 je: " + pol.getCoefAt(3));
        System.out.println("derivace : " + pol.derivate());
        System.out.println("");
        System.out.println("pro x=1 je vysledek: " + pol.compteValue(1));
        System.out.println("pro x=2 je vysledek: " + pol.compteValue(2));

        //finta aby nemusela vytvářet pole - Polynom - tovární metody změní tři tečky!!!
        Polynom p1 = Polynom.getInstance(5, 3, 0, 6);
        System.out.println(p1);
        Polynom p2 = Polynom.getInstance(6, 36, 1);
        System.out.println(p2);
        System.out.println("pro x = 3 vysledek: " + p2.compteValue(3));
        System.out.println("součet dvou polynomů: " + Polynoms.sum(p1, p2));
        System.out.println("");
        System.out.println("součin p1 a p2 : " + Polynoms.multiply(p1, p2));
        System.out.println("");
        System.out.println("integrace: " + pol.integrate(0, 2));
        System.out.println("");
        System.out.println("Inegral: " + pol.integral());

    }

}
