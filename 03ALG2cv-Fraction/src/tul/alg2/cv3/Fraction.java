package tul.alg2.cv3;

/**
 * třída reprezentující zlomek umožnit: zadání 2 čísly nebo txt get: čitatel,
 * jmenovatel toString čitatel/jmenovatel zjednodušit zlomek - euklid
 *
 * @author MP
 */
public class Fraction {

    //data
    private int cit; //citatel
    private int jm; //jmenovatel

    //kontruktor
    public Fraction(int cit, int jm) {
        this.cit = cit;
        this.jm = checkJmenovatel(jm);
        zjednodus();
    }

    //funguje pouze pro citatele nebo jmenovatele = 1...9
    public Fraction(String zlomek) {
        char c = zlomek.charAt(0);
        char j = zlomek.charAt(2);
        this.cit = Character.getNumericValue(c);
        this.jm = Character.getNumericValue(j);
        zjednodus();
    }

    //metody
    private int nejD() {
        int a = cit;
        int b = jm;
        int c;
        while (a != 0) {
            c = a;
            a = b % a;
            b = c;
        }
        return b;
    }

    private void zjednodus() {
        int delitel = nejD();
        if (Math.abs(delitel) > 1) {
            jm = jm / delitel;
            cit = cit / delitel;
        }

        if (jm < 0 && cit > 0) {
            jm = jm * (-1);
            cit = cit * (-1);
        }
    }

    private int checkJmenovatel(int jm) {
        if (jm == 0) {
            throw new IllegalArgumentException("Jmenovatel != 0!");
        }
        return jm;
    }

    //getters
    public int getCit() {
        return cit;
    }

    public int getJm() {
        return jm;
    }

    //setters
    public void setCit(int cit) {
        this.cit = cit;
        zjednodus();
    }

    public void setJm(int jm) {
        this.jm = checkJmenovatel(jm);
        zjednodus();
    }

    @Override
    public String toString() {
        return cit + "/" + jm;
    }

}
