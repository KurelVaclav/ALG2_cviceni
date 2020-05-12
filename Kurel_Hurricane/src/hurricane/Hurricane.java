package hurricane;

/**
 *
 * @author Václav Kurel
 */
public class Hurricane implements Comparable<Hurricane>{

    private int year;
    private String month;
    private int pressure;
    private int speed;
    private String name;
    static final double CONVERTERCONSTANT = 1.852;

    public Hurricane(int year, String month, int pressure, int speed, String name) {
        if (year < 0 || pressure < 0 || speed < 0) {
            throw new IllegalArgumentException("Nevalidní data(záporná)!!!");
        }
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
    }


    public double getSpeedInKmH() {
        return speed * CONVERTERCONSTANT;
    }

    public int getScale() {
        double speedInKmH = getSpeedInKmH();
        if (speedInKmH >= 252) {
            return 5;
        } else if (speedInKmH >= 209) {
            return 4;
        } else if (speedInKmH >= 178) {
            return 3;
        } else if (speedInKmH >= 154) {
            return 2;
        } else if (speedInKmH >= 119) {
            return 1;
        }
        return 0;
    }

    @Override
    public int compareTo(Hurricane o) {
        return this.speed - o.speed;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-11s%-11d%-21d%-16s",year,month,pressure,speed,name);
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
