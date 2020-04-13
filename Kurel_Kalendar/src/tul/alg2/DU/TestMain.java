package tul.alg2.DU;

import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class TestMain {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Kalendar kalendar1 = new Kalendar(1, 6, 2020);
        kalendarMenu(kalendar1);
    }

    public static void kalendarMenu(Kalendar k) {
        boolean end = false;
        int choice;
        while (!end) {
            System.out.printf(k.buildKalendar());
            System.out.println("");
            System.out.println("Pro další měsíc zadejte:      1");
            System.out.println("Pro předchozí měsíc zadejte:  2");
            System.out.println("Pro konec procházení zadejte: 0");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    end = true;
                    break;
                case 1:
                    k.nextMM();
                    System.out.printf(k.buildKalendar());
                    System.out.println("");
                    break;
                case 2:
                    k.previousMM();
                    System.out.printf(k.buildKalendar());
                    System.out.println("");
                    break;
                default:
                    System.out.println("Neplatná volba");
            }
        }
    }

}
