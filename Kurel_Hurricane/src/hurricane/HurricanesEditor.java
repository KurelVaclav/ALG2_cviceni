package hurricane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class HurricanesEditor implements HurricaneInterface {

    private ArrayList<Hurricane> hurricanes = new ArrayList<>();

    @Override
    public void loadHurricanes() {
        File hurricanesFile = new File("hurricanedata.txt");
        try {
            Scanner inData;
            inData = new Scanner(hurricanesFile);
            int year, pressure, speed;
            String month, name;
            while (inData.hasNext()) {
                try {
                    year = inData.nextInt();
                    month = inData.next();
                    pressure = inData.nextInt();
                    speed = inData.nextInt();
                    name = inData.next();
                    Hurricane h = new Hurricane(year, month, pressure, speed, name);
                    hurricanes.add(h);
                } catch (Exception e) {
                    throw new RuntimeException("Špatný soubor!!!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor nenalezen!!!");
        }

    }

    @Override
    public String info(int yearFrom, int yearTo) {
        int year;
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-11s%-11s%-21s%-16s%n", "Year", "Month", "Pressure[Mb]", "Speed[knot/h]", "Name"));
        for (Hurricane hurricane : hurricanes) {
            year = hurricane.getYear();
            if (year >= yearFrom && year <= yearTo) {
                sb.append(hurricane).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String info(String name) {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-11s%-11s%-21s%-16s%n", "Year", "Month", "Pressure[Mb]", "Speed[knot/h]", "Name"));
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.getName().equalsIgnoreCase(name)) {
                sb.append(String.format("%-11s%-11d%-12.2f", hurricane.getName(), hurricane.getScale(), hurricane.getSpeedInKmH()));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String infoSortedBySpeed() {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-11s%-11s%-21s%-16s%n", "Year", "Month", "Pressure[Mb]", "Speed[knot/h]", "Name"));
        Collections.sort(hurricanes);
        for (Hurricane hurricane : hurricanes) {
            sb.append(hurricane).append("\n");
        }
        return sb.toString();
    }

}
