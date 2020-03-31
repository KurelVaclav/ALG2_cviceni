package tul.alg2.cv3;

import java.util.Arrays;

/**
 * 3 úrovně Jaké polynom jaké data 5*x**3+3*x**2+6 Co zapamatovat??? pole[6 0 3
 * 5] co bude poskytovat
 *
 * @author MP
 */
public class Polynom {

    //1. data
    private double[] coef; //zapouzdřit data = private

    //2. konstruktory
    //zadávat obráceně lehčí pro uživatele [5 3 0 6] nebo rovnou koeficienty 5,3,0,6
    //použiji tovární metodu místi konstruktoru
    //private aby sem tvořil přes tovární metodu - poví nám referenci a já si to zapíšu
    // abych dodržel private = defenzivní kopie vždy u pole NE len priradit referenciu pola
    private Polynom(double[] coef) {
        //defenzivní kopie
        double[] coefTemp = new double[coef.length]; //def.kop aby se mi uživ nedostal k hodnotám toho pola - aby byly privátní i hodnoty pola
        //pro kopírování for nebo arrayCopy
        System.arraycopy(coef, 0, coefTemp, 0, coef.length); //coef.length - počet prvnků
        this.coef = coefTemp;
    }

    //tovární factory metoda pro neomezený množnství konstruktorů
    public static Polynom getInstanceReverted(double[] coef) { //pro pole [6 0 3 5]
        return new Polynom(coef);
    }

    public static Polynom getInstance(double... coef) { // pro pole [5 3 0 6] double...coef 5,3,0,6
        //přetočit pole - poslední = první
        double[] coefTemp = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            coefTemp[coefTemp.length - 1 - i] = coef[i];
        }
        return new Polynom(coefTemp);
    }

    //3. metody - ze zadání vyzjistím co s tím DU: výpočet konkrétní hodnoty polynomu pro zadané x
    //TODO 1.
    //y = 5x3 +  3x2 + 6 pro x = 1 y = 5 + 3 + 6 = 14 AND vypočítat přes hornerovo schéma
    public double compteValue(double x) {
//        double vysl = 0;
//        for (int i = 0; i < coef.length; i++) {
//            vysl = vysl + coef[i]*Math.pow(x, i);
//        }
        double vysl = coef[coef.length - 1];
        for (int i = coef.length - 1; i > 0; i--) {
            vysl = vysl * x + coef[i - 1];
        }
        return vysl;
    }

    //getters
    //koeficienty na zadaném indexe
    public double getCoefAt(int exponent) {
        return coef[exponent];
    }

    //na všechny koeficitenty len v opačnom poradí
    public double[] getAllCoef() {
//        return coef; //chceme poslat defenzivní kopii, jen hodnoty, NE kde je máme uložene!!!
        //místo arrayCopy m´použije něco majšstrstik
        return Arrays.copyOf(coef, coef.length); //díky tomu bude pole na jiné adrese, ale obsahovat stejné hodnoty jako máme my uložené
    }

    //vrátí stupeň polynomu
    public int getDegree() {
        return coef.length - 1; //poslední index pole
    }

    //textová reprezentace  - vygenerovat
    @Override
    public String toString() {
//       return "Polynom{ coef = " + Arrays.toString(coef) + '}';
        String pol = "";
        if (coef[coef.length - 1] < 0) {
            pol = "-";
        }
        for (int i = coef.length - 1; i > 0; i--) {
            if (coef[i] != 0) {
                pol = pol + Math.abs(coef[i]) + "x^" + i;
                if (coef[i - 1] >= 0) {
                    pol = pol + "+";
                } else {
                    pol = pol + "-";
                }
            }
        }
        pol = pol + coef[0];
        return pol;
    }

    //TODO 2. nahradit sofistikovaným výpisem - matematicky správně: 5x^3 + 3x^2 + 6
    //metoda derivace - out zase polynom
    public Polynom derivate() {
        double[] coefDiff = new double[coef.length - 1]; //this pouze aby lépe našla coef, derivace o jedno menší exp x-ka
        for (int i = 0; i < coefDiff.length; i++) {
            coefDiff[i] = coef[i + 1] * (i + 1);
        }
        return new Polynom(coefDiff); // mohu vrátit rovnou ten polynom přes konstruktor
    }

    //TODO Bonus: integrál na zadaném indexu
    public double integrate(double a, double b) {
        double vysl;
        double[] coefIntegr = new double[coef.length + 1];
        for (int i = 1; i < coefIntegr.length; i++) {
            coefIntegr[i] = coef[i - 1] / i;
        }
        Polynom p = new Polynom(coefIntegr);
        vysl = p.compteValue(b) - p.compteValue(a);
        return vysl;
    }
    
    public Polynom integral(){
        double[] coefIntegr = new double[coef.length + 1];
        for (int i = 1; i < coefIntegr.length; i++) {
            coefIntegr[i] = coef[i - 1] / i;
        }
        return new Polynom(coefIntegr);
    }
}
