package alg2.cv.PoleObjectBody;

import java.util.Scanner;

/**
 *
 * @author MP
 */
public class Area {

    private Scanner sc = new Scanner(System.in);
    private Bod[] pole;
    private int n; //počet vrcholů n-úhelníka

    public Area(Bod[] pole) {
        this.pole = pole;
    }

    public Area(Bod[] pole, int n) {
        this.pole = pole;
        this.n = n;
    }

    public void nactiBody() {
        System.out.println("Zadej počet bodů n: ");
        n = sc.nextInt();
        pole = new Bod[n];
        for (int i = 0; i < this.n; i++) {
            System.out.println("Zadej souřadnici x: ");
            double x = sc.nextDouble();
            System.out.println("Zadej souřadnici y: ");
            double y = sc.nextDouble();
            pole[i] = new Bod(x, y);
        }
    }

    public int getN() {
        return n;
    }

    public void vypisPole() {
        for (Bod a : pole) {
            System.out.println(a);
        }
    }

    public double obvod() {
        double obvod = 0;
        for (int i = 0; i < pole.length - 1; i++) {
            obvod = obvod + pole[i].vzdalenostOd(pole[i + 1]);
        }
        obvod = obvod + pole[0].vzdalenostOd(pole[pole.length - 1]);
        return obvod;
    }

    public double obsah() {
        double obsah = 0;
        for (int i = 0; i < pole.length - 1; i++) {
            obsah = obsah + (pole[i].getX() * pole[i + 1].getY() - pole[i + 1].getX() * pole[i].getY());
        }
        obsah = obsah + pole[0].getX() * pole[pole.length - 1].getY() - pole[pole.length - 1].getX() * pole[0].getY();
        obsah = (1 / 2.0) * Math.abs(obsah);
        return obsah;
    }
}
