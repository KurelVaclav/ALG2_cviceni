package competition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Načte si data ze souboru, pracuje s nimi a pak je zase uloží do nového
 * souboru
 *
 * @author Václav Kurel
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Competition c = new Competition();
        //nekonečná smyčka - dokud nezadá správný soubor, tak se přeskakuje break...
        try {
            while (true) {
                try {
                    System.out.println("Zadej názvy vstupních souborů: ");
                    String startFile = sc.next(); //cesta k souborům
                    String finishFile = sc.next();
                    c.load(startFile, finishFile); //načtu soubory, vyjímku bud ošetřím nebo hodím výš - ošetřím tu! try-catch
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadaný špatný soubor!");
                }
            }
            System.out.println(c.getResults()); //výstup z logiky, výsledkem String, sout jen v Main
            //System.out.println("Zadej název výstupního souboru");
            //String resultFile = sc.next();
            //c.saveResults(resultFile);
        } catch (IOException e) {
            System.out.println("Chyba při čtení a zápisu!");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

}
