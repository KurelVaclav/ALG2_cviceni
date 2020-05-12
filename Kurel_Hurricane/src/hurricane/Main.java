package hurricane;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static HurricaneInterface hi;
    public static boolean end = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            hi = new HurricanesEditor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        hi.loadHurricanes();
        while (!end) {
            try {
                runMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void runMenu() {
        System.out.println("Vyberte možnost čísicí: ");
        System.out.println("1 - info o hurikánech ve zvoleném období");
        System.out.println("2 - info o zvoleném hurikánu");
        System.out.println("3 - info o všech hurikánech seřazený dle rychlosti");
        System.out.println("4 - konec aplikace");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Zadejte rok od: ");
                int yearFrom = sc.nextInt();
                System.out.println("Zadejte rok do: ");
                int yearTo = sc.nextInt();
                System.out.println(hi.info(yearFrom, yearTo));
                break;
            case 2:
                System.out.println("Zadejte jméno hurikánu: ");
                String name = sc.next();
                System.out.println(hi.info(name));
                break;
            case 3:
                System.out.println(hi.infoSortedBySpeed());
                break;
            case 4:
                end = true;
                break;
            default:
                System.out.println("Zadejte číslo 1 až 4");
        }
    }

    //test main
//    public static void main(String[] args) {
//        hi = new HurricanesEditor();
//        hi.loadHurricanes();
//        System.out.println("Zadejte rok od: ");
//        int yearFrom = sc.nextInt();
//        System.out.println("Zadejte rok do: ");
//        int yearTo = sc.nextInt();
//        System.out.println(hi.info(yearFrom,yearTo));
//        System.out.println("Zadejte jméno hurikánu: ");
//        String name = sc.next();
//        System.out.println(hi.info(name));
//        System.out.println(hi.infoSortedBySpeed());
//        
//    }
}
