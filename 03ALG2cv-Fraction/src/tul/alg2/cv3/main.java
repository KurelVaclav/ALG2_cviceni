package tul.alg2.cv3;

/**
 *
 * @author MP
 */
public class main {

    public static void main(String[] args) {
        Fraction zlomek1 = new Fraction(12, 8);
        System.out.println(zlomek1);
        Fraction zlomek2 = new Fraction("6/8"); //funguje pouze pro 1...9 čísla
        System.out.println(zlomek2);
        Fraction z1 = new Fraction(2, 5);
        System.out.println("z1 = " + z1);
        Fraction z2 = new Fraction(3, 2);
        System.out.println("z2 = " + z2);
        System.out.println("Součet " + z1 + " + " + z2 + " = " + FractionsCalcurator.plus(z1, z2));
        System.out.println("Odečet " + z1 + " - " + z2 + " = " + FractionsCalcurator.minus(z1, z2));
        System.out.println("násobení " + z1 + " * " + z2 + " = " + FractionsCalcurator.vynasob(z1, z2));
        System.out.println("dělení " + z1 + " / " + z2 + " = " + FractionsCalcurator.vydel(z1, z2));

    }

}
