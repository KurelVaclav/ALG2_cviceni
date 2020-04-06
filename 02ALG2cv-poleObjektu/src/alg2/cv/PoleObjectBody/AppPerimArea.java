package alg2.cv.PoleObjectBody;

//import java.util.Arrays;
import java.util.Scanner;

/**
 * výpočet obvodu a plochy pole objektů typu Bod
 *
 * @author MP
 */
public class AppPerimArea {

    static Bod[] pole; //vytáhnutí nahoru pole!!!

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej počet bodů n: ");
        int n = sc.nextInt();
        //načtení n bodů do pole
        pole = new Bod[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Zadej souřadnici x: ");
            double x = sc.nextDouble();
            System.out.println("Zadej souřadnici y: ");
            double y = sc.nextDouble();
//            Bod p = new Bod(x, y);
//            pole[i] = p;
            pole[i] = new Bod(x, y);
        }
        //výpis pole bodů
//        System.out.println(Arrays.toString(pole));
        vypisPole();

        System.out.println("obvod = " + obvod());
        System.out.println("obvod Marketa = " + obvodMarketa());
        System.out.println("obsah = " + obsah());

    }

    //metody6
    private static void vypisPole() {
        for (Bod a : pole) { //čtení FOR-EACH: pro každě a z pole "pole" udělej:
            System.out.println(a);
        }
    }

    //od markety obvod
    public static double obvodMarketa() {
        double obvod = 0;
        for (int i = 0; i < pole.length - 1; i++) {
            obvod = obvod + pole[i].vzdalenostOd(pole[i + 1]);
        }
        obvod = obvod + pole[0].vzdalenostOd(pole[pole.length - 1]); // od prvého bodu do posledného
        return obvod;
    }

    private static double obvod() {
        double obvod = 0;
        for (int i = 0; i < pole.length - 1; i++) {
            double soucet = Math.hypot(pole[i].getX() - pole[i + 1].getX(), pole[i].getY() - pole[i + 1].getY());
            obvod = obvod + soucet;
        }
        obvod = obvod + pole[0].vzdalenostOd(pole[pole.length - 1]);
        return obvod;
    }

    public static double obsah() {
        double obsah = 0;
        for (int i = 0; i < pole.length - 1; i++) {
            obsah = obsah + (pole[i].getX() * pole[i + 1].getY() - pole[i + 1].getX() * pole[i].getY());
        }
        obsah = obsah + pole[0].getX() * pole[pole.length - 1].getY() - pole[pole.length - 1].getX() * pole[0].getY();
        obsah = (1 / 2.0) * Math.abs(obsah);
        return obsah;
    }

}

//pole třech bodů
//        Bod[] pole = new Bod[3];
//        double x = 2.4;
//        double y = 3.1;
//        Bod p = new Bod(x, y);
//        pole[0] = p;
