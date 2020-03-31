package tul.alg2.cv3;

/**
 * library class knihovní třída = jen statické metody - zgrupím podobné metody
 * do jedné třídy automaticky má defaultní polynom - nechceme tvořit objekty =>
 * prázdný konstruktor
 *
 * @author MP
 */
public class Polynoms {

    //prázdný kontruktor - nelze vytvořit objekt z této třídy :)
    private Polynoms() {
        //nemůže se vytvořit objekt
    }

    public static Polynom sum(Polynom a, Polynom b) {
        // zjištění který je vyššího řádu: - nová třída do Polynomu
        boolean isABigger = a.getDegree() > b.getDegree();
        Polynom max = isABigger ? a : b; //nebo Math.max(a.getDegree(), b.getDegree())
        Polynom min = isABigger ? b : a;

        double[] sumCoef = new double[max.getDegree() + 1];
        // 6 0 3 5
        //+1 3 6
        //=7 3 9 5
        for (int i = 0; i < max.getDegree() + 1; i++) {
            sumCoef[i] = max.getCoefAt(i);
        }
        for (int i = 0; i < min.getDegree() + 1; i++) {
            sumCoef[i] = sumCoef[i] + min.getCoefAt(i);
        }
        return Polynom.getInstanceReverted(sumCoef);
    }

    //TODO - součin dvou polynomů
    public static Polynom multiply(Polynom a, Polynom b) {
        int sizeA = a.getDegree();
        int sizeB = b.getDegree();
        double[] p1 = a.getAllCoef();
        double[] p2 = b.getAllCoef();
        double[] multiCoef = new double[sizeA + sizeB + 1];
        for (int i = 0; i < sizeA + sizeB + 1; i++) {
            multiCoef[i] = 0;
        }
        for (int i = 0; i < sizeA + 1; i++) {
            for (int j = 0; j < sizeB + 1; j++) {
                multiCoef[i + j] = multiCoef[i + j] + (p1[i] * p2[j]);
            }
        }
        return Polynom.getInstanceReverted(multiCoef);
    }
    
}
