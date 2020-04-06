package alg2.cv.PoleObjectBody;

import java.util.Scanner;

/**
 * pole Bodů z elearningu
 *
 * @author MP
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static Bod[] pole;

    public static void main(String[] args) {

//        Area a1 = new Area(pole);
//        a1.nactiBody();
//        a1.vypisPole();
//        System.out.println(a1.getN() + " - úhelník");
//        System.out.println("Obvod = " + a1.obvod());
//        System.out.println("Obsah = " + a1.obsah());
        boolean end = false;
        while (!end) {
            System.out.println("Tento program ti poskytne výpočet n-úhelníka");
            Area a1 = new Area(pole);
            a1.nactiBody();
            System.out.println(a1.getN() + " - úhelník");
            System.out.println("Co chcete vypočítat: ");
            System.out.println("Zadejte: ");
            System.out.println("1 pro obvod");
            System.out.println("2 pro obsah");
            System.out.println("3 pro vzdálenost od počátku");
            System.out.println("4 pro vzdálenost od bodu");
            int odpoved = sc.nextInt();
            switch (odpoved) {
                case 1:
                    System.out.println("o = " + a1.obvod());
                    break;
                case 2:
                    System.out.println("S = " + a1.obsah());
                    break;
                case 3:
                    System.out.println("Zadejte souřadnici x daného bodu");
                    double x = sc.nextDouble();
                    System.out.println("Zadejte souřadnici y daného bodu");
                    double y = sc.nextDouble();
                    Bod b1 = new Bod(x, y);
                    System.out.println("vzdalenost od počátku = " + b1.vzdalenostOdPocatku());
                    break;
                case 4:
                    System.out.println("Zadejte souřadnici x daného bodu");
                    double x1 = sc.nextDouble();
                    System.out.println("Zadejte souřadnici y daného bodu");
                    double y1 = sc.nextDouble();
                    Bod b2 = new Bod(x1, y1);
                    System.out.println("Zadejte souřadnici x od kterého bodu");
                    double x2 = sc.nextDouble();
                    System.out.println("Zadejte souřadnici y od kterého bodu");
                    double y2 = sc.nextDouble();
                    Bod b3 = new Bod(x2, y2);
                    System.out.println("vzdalenost od bodu [" + b3.getX() + ", " + b3.getY() + "] = " + b2.vzdalenostOd(b3));
                    break;
                default:
                    System.out.println("Neplatná volba");
            }
            System.out.println("Chcete zadat další n-úhelník? a/n");
            String answ = sc.next();
            end = answ.toLowerCase().charAt(0) == 'n';

        }

    }

}
