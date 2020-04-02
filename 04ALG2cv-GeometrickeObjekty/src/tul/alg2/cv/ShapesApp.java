package tul.alg2.cv;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class ShapesApp {

    /**
     * @param args the command line arguments
     */
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Shape> shapes = new ArrayList<>();
    //protected se týká dědíčnosti

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = readChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    clearObjects();
                    break;

            }
        } while (choice != 0);

    }

    private static void displayMenu() {

    }

    private static int readChoice() {
        return 0;
    }

    private static void clearObjects() {

    }

}
