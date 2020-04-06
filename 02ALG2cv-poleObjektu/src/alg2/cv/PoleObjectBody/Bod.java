package alg2.cv.PoleObjectBody;

/**
 * elearning úloha 1 - pole bodů
 *
 * @author MP
 */
public class Bod {

    //data = uchovat souřadnice
    private double x;
    private double y;

    //inicializace objektů = kontruktory
    public Bod(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //settery - nedávat, raz urobíš bod a už ho nemůžeš změnit
    //poskytni hodnoty = gettery
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //toString automaticky bez vyzvání :D
    @Override
    public String toString() {
        return "Bod{" + "x=" + x + ", y=" + y + '}';
    }

    //zjistite vzdalenost bodu od počátku = metoda
    public double vzdalenostOdPocatku() {
        double vzdalenost = Math.hypot(this.x, this.y);
        return vzdalenost;
    }

    //vzdálenost bodu od jiného bodu = metoda
    public double vzdalenostOd(Bod b) {
        double vzdX = Math.abs(this.x - b.x);
        double vzdY = this.y - b.y;
        double vzd = Math.hypot(vzdX, vzdY); //vzd = Math.sqrt(vzdX*vzdX + vzdY*vzdY);
        return vzd;
    }

//    //testovací main
//    public static void main(String[] args) {
//        Bod b1 = new Bod(1, 6);
//        Bod b2 = new Bod(4, 10);
//        System.out.println(b1.vzdalenostOd(b2)); //5
//        System.out.println(b1.vzdalenostOdPocatku()); //6,08
//    }
    
}
