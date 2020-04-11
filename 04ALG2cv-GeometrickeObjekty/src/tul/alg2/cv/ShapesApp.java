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
                    addObjects();
                    break;
                case 2:
                    System.out.println("Celkový obsah plochy: " + computeAllArea());
                    break;
                case 3:
                    printObjects();
                    break;
                case 4:
                    findMaxArea();
                    break;
                case 5:
                    shapes.clear();
                    break;
                case 6:
                    sortByArea();
                    break;
                default:
                    System.out.println("neplatná volba!");
            }
        } while (choice != 0);

    }

    private static void displayMenu() {
        System.out.println("------------------------------------------------------");
        System.out.println("Vyberte jednu z možností");
        System.out.println("0 - konec programu");
        System.out.println("1 - zadat sadu geometrických útvarů");
        System.out.println("2 - výpočet celkového obsahu všech útvarů dohromady");
        System.out.println("3 - výčet všech zadaných objektů");
        System.out.println("4 - info o objektě s maximálním obsahem");
        System.out.println("5 - zadání nové sady tvarů");
        System.out.println("------------------------------------------------------");
    }

    private static int readChoice() {
        int answer = sc.nextInt();
        if (answer < 0 || answer > 5) {
            System.out.println("Neplatná volba!");
        }
        return answer;
    }

    private static void displayObjectMenu() {
        System.out.println("---------------");
        System.out.println("0 - návrat do Menu");
        System.out.println("Chcete přidat");
        System.out.println("1 - Čtverec");
        System.out.println("2 - Obdélník");
        System.out.println("3 - Kruh");
        System.out.println("4 - Elipsu");
        System.out.println("5 - Trojúhelník");
        System.out.println("---------------");
        System.out.println();
    }

    private static void addObjects() {
        double r;
        double a;
        double b;
        double c;
        int choice;
        do {
            displayObjectMenu();
            choice = readChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Zadej délku strany čtverce a: ");
                    a = sc.nextDouble();
                    shapes.add(new Square(a));
                    break;
                case 2:
                    System.out.println("Zadej stranu obdélníka a: ");
                    a = sc.nextDouble();
                    System.out.println("Zadej stranu obdélníka b: ");
                    b = sc.nextDouble();
                    shapes.add(new Rectangle(a, b));
                    break;
                case 3:
                    System.out.println("Zadej poloměr kruhu r: ");
                    r = sc.nextDouble();
                    shapes.add(Circle.getInstanceR(r));
                    break;
                case 4:
                    System.out.println("Zadej délku poloosy elipsy a: ");
                    a = sc.nextDouble();
                    System.out.println("Zadej délku poloosy elispy b: ");
                    b = sc.nextDouble();
                    shapes.add(new Ellipse(a, b));
                    break;
                case 5:
                    System.out.println("Zadej stranu trojúhelníku a: ");
                    a = sc.nextDouble();
                    System.out.println("Zadej stranu trojúhelníka b: ");
                    b = sc.nextDouble();
                    System.out.println("Zadej stranu trojúhelníka c: ");
                    c = sc.nextDouble();
                    shapes.add(new Triangle(a, b, c));
                    break;
                default:
                    System.out.println("Neplatná volba!");
            }
        } while (choice != 0);
    }

    private static double computeAllArea() {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.computeArea();
        }
        return area;
    }

    private static void printObjects() {
        System.out.println("Zadali jste tyto tvary: ");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    private static void findMaxArea() {
        double max = 0;
        int imax = -1;
        for (int i = 0; i < shapes.size(); i++) {
            if (max < shapes.get(i).computeArea()) {
                imax = i;
                max = shapes.get(i).computeArea();
            }
        }
        System.out.println("Tvar s největším obsahem je " + shapes.get(imax) + ", jeho obsah je " + shapes.get(imax).computeArea());
    }

    //TODO zobrazit objekty setříděné podle plochy volat collections.sort + zaručit typovou kompatibilitu - obsahovat compareTo()
    private static void sortByArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
