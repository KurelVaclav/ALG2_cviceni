package hurricane;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static HurricaneInterface hi;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HurricanesEditor he = new HurricanesEditor();
        he.loadHurricanes();
        System.out.println("Zadejte rok od: ");
        int yearFrom = sc.nextInt();
        System.out.println("Zadejte rok do: ");
        int yearTo = sc.nextInt();
        System.out.println(he.info(yearFrom,yearTo));
        System.out.println("Zadejte jméno hurikánu: ");
        String name = sc.next();
        System.out.println(he.info(name));
        System.out.println(he.infoSortedBySpeed());
        
    }

}
