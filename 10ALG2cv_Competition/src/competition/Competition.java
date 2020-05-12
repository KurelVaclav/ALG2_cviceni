package competition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class Competition {

    //data
    private ArrayList<Runner> runners = new ArrayList<>();

    public void load(String startFilePath, String finishFilePath) throws FileNotFoundException, IOException {
        //načítání po znacích - přednáška (kopie souborů), Scanner umí načítat i ze souborů
        //String obalit do třídy File, načítání pomocí Scanner
        File startFile = new File(startFilePath);
        //3 možnosti ošetření vyjímky: v kódu (výzva opět zadat název souboru X nekomunikuji s uživatelem tu
        // vyjímku vyhodím vyš - load bude vyhazovat vyjímku a bude se o to starat ten kdo volá load()
        Scanner inStart = new Scanner(startFile); //ucho napojené na soubor, hasNext - zjistím konec souboru
        while (inStart.hasNext()) {
            int number = inStart.nextInt();
            String firstName = inStart.next();
            String lastName = inStart.next();
            String startTime = inStart.next();
            Runner r = new Runner(number, firstName, lastName);
            r.setStartTime(startTime);
            runners.add(r);
        }

        //načítání pomocí BufferedReader - umožní číst celé řádky
        File finishFile = new File(finishFilePath);
        //budeme obalovat File do FileReader a ten do BufferedReader
        BufferedReader inFinish = new BufferedReader(new FileReader(finishFile));
        //readLine vrací String a když tam nic není vrací null
        String line;
        while ((line = inFinish.readLine()) != null) { //102 10:02:00:000
            String[] parts = line.split("[ ]+");//oddělovač mezery
            Runner r = findRunner(Integer.parseInt(parts[0]));
            r.setFinishTime(parts[1]);
        }
    }

    public String getResults() {
        //třídení
        Collections.sort(runners);
        StringBuilder sb = new StringBuilder("");
        int n = 1; // pořadové číslo
        for (Runner runner : runners) {
            sb.append(String.format("%-2d. %s%n", n, runner));
            n++;
        }
        return sb.toString();
    }

    public void saveResults(String resultFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Runner findRunner(int number) {
        for (Runner runner : runners) {
            if (runner.getNumber() == number) {
                return runner;
            }
        }
        //vyjímka že jsme nenašli běžce, RunTimeException - nepovinně ošetřovatelná vyjímka X tato povinně OV
        throw new NoSuchElementException("Běžec s číslem " + number + " nebyl na startu!");
    }

}
