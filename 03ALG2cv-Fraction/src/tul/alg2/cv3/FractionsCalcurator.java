package tul.alg2.cv3;

/**
 *
 * @author MP
 */
public class FractionsCalcurator {

    //zamezení volání jako objekt
    private FractionsCalcurator() {

    }

    public static Fraction plus(Fraction z1, Fraction z2) {
        int citZ1 = z1.getCit() * z2.getJm();
        int citZ2 = z2.getCit() * z1.getJm();
        int spolJm = z1.getJm() * z2.getJm();
        return new Fraction(citZ1 + citZ2, spolJm);
    }

    public static Fraction minus(Fraction z1, Fraction z2) {
        int citZ1 = z1.getCit() * z2.getJm();
        int citZ2 = z2.getCit() * z1.getJm();
        int spolJm = z1.getJm() * z2.getJm();
        return new Fraction(citZ1 - citZ2, spolJm);
    }

    public static Fraction vynasob(Fraction z1, Fraction z2) {
        int cit = z1.getCit() * z2.getCit();
        int jm = z1.getJm() * z2.getJm();
        return new Fraction(cit, jm);
    }

    public static Fraction vydel(Fraction z1, Fraction z2) {
        Fraction reverseZ2 = new Fraction(z2.getJm(), z2.getCit());
        return vynasob(z1, reverseZ2);
    }
}
