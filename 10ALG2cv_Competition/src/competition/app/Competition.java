package competition.app;

import competition.utils.IllegalFileNameException;
import competition.filehandling.BinaryWriter;
import competition.filehandling.TextWriter;
import competition.filehandling.Writer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.activation.UnsupportedDataTypeException;

/**
 * vyjímky - RunTimeE nemusím ošetřovat - nullpointer, aritmetic,
 * arrayoutofbound,illegal ošetřovat musím hlavně u file regulární výraz při
 * kontrole parametrů vlastní vyjímka
 *
 * @author Václav Kurel
 */
public class Competition {

    //data
    private ArrayList<Runner> runners = new ArrayList<>();

    //TODO načíst .dat nebo .txt
    public void loadFromTeacher(String startFilePath, String finishFilePath) throws FileNotFoundException, IOException {
        //start2019, Start2019, start19 2000 2030 
        // test regex java - freeformatter.com
        //?...0 nebo jednou;\d jakýkoliv číslo, zde musí splňovat regulární výraz!
//        if (!startFilePath.matches("^[Ss]tart(20)?([0-2][0-9]|30)")) {
//            throw new IllegalArgumentException("Nepodporovaný název souboru!");
//        }
//        if(!startFilePath.contains("start")){
//            throw new IllegalArgumentException("Start soubor musí obsahovat start!");
//        }
        //vyhazování vlastních vyjímek
        if (!startFilePath.contains("start")) {
            throw new IllegalFileNameException("Start soubor musí obsahovat start!");
        }
        //načítání po znacích - přednáška (kopie souborů), Scanner umí načítat i ze souborů
        //String obalit do třídy File, načítání pomocí Scanner
        File startFile = new File(startFilePath);
        //3 možnosti ošetření vyjímky: v kódu (výzva opět zadat název souboru X nekomunikuji s uživatelem tu
        // vyjímku vyhodím vyš - load bude vyhazovat vyjímku a bude se o to starat ten kdo volá load()
        try (Scanner inStart = new Scanner(startFile)) { //ucho napojené na soubor, hasNext - zjistím konec souboru
            while (inStart.hasNext()) {
                int number = inStart.nextInt();
                String firstName = inStart.next();
                String lastName = inStart.next();
                String startTime = inStart.next();
                Runner r = new Runner(number, firstName, lastName);
                r.setStartTime(startTime);
                runners.add(r);
            }
        }

        //načítání pomocí BufferedReader - umožní číst celé řádky
        File finishFile = new File(finishFilePath);
        //budeme obalovat File do FileReader a ten do BufferedReader
        try (BufferedReader inFinish = new BufferedReader(new FileReader(finishFile))) { //obalení try with resouces
            //readLine vrací String a když tam nic není vrací null
            String line;
            while ((line = inFinish.readLine()) != null) { //102 10:02:00:000
                String[] parts = line.split("[ ]+");//oddělovač mezery
                try {
                    Runner r = findRunner(Integer.parseInt(parts[0]));
                    r.setFinishTime(parts[1]);
                } catch (NoSuchElementException e) {
                    System.err.print(e.getMessage());
                }
            }
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

    //podle uživatele uložit do binárního - pouze v bytech , nebo txt
    public void saveResults(String resultFilePath) throws IOException {
        Collections.sort(runners);
        Writer w;
        if (resultFilePath.endsWith(".txt")) { //zapsat do text
            w = new TextWriter();
            System.out.println("text");
        } else if (resultFilePath.endsWith(".dat")) { //zapsat binárně
            w = new BinaryWriter();
        } else { //vyjímka: takový soubor nepodporujem
            throw new IllegalArgumentException("Taková koncovka souboru není podporována!!");
        }
        w.saveResults(resultFilePath, runners);
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

    public void load(String startFilePath, String finishFilePath) throws FileNotFoundException, IOException {
        if (startFilePath.endsWith(".txt")) {
            loadStartText(startFilePath);
        } else if (startFilePath.endsWith(".dat")) {
            loadStartBinary(startFilePath);
        } else {
            throw new UnsupportedDataTypeException("Nepodporovaný formát!");
        }
        if (finishFilePath.endsWith(".txt")) {
            loadFinishText(finishFilePath);
        } else if (finishFilePath.endsWith(".dat")) {
            loadFinishBinary(finishFilePath);
        } else {
            throw new UnsupportedDataTypeException("Nepodporovaný formát!");
        }
    }

    private void loadStartText(String startFilePath) throws FileNotFoundException {
        File startFile = new File(Writer.dataDirectory,startFilePath);
        try (Scanner in = new Scanner(startFile)) {
            while (in.hasNext()) {
                int number = in.nextInt();
                String firstName = in.next();
                String lastName = in.next();
                String startTime = in.next();
                try {
                    Runner r = new Runner(number, firstName, lastName);
                    r.setStartTime(startTime);
                    runners.add(r);
                } catch (NoSuchElementException ex) {
                    System.err.println("Běžec nenalezen!!");
                }
            }
        }
    }

    private void loadStartBinary(String startFilePath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        startFilePath = Character.toUpperCase(startFilePath.charAt(0)) + startFilePath.substring(1);
        bw.createStart(startFilePath);
        File startFile = new File(Writer.dataDirectory,startFilePath);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(startFile))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    long startTimeNano = dis.readLong();
                    String startTime = (LocalTime.ofNanoOfDay(startTimeNano).format(Runner.dtfStart));
                    try {
                        Runner r = new Runner(number, firstName, lastName);
                        r.setStartTime(startTime);
                        runners.add(r);
                    } catch (NoSuchElementException ex) {
                        System.err.println("Běžec nenalezen!!");
                    }
                } catch (EOFException ex) {
                    isEnd = true;
                }
            }
        }
    }

    private void loadFinishText(String finishFilePath) throws FileNotFoundException, IOException {
        File finishFile = new File(Writer.dataDirectory,finishFilePath);
        try (BufferedReader in = new BufferedReader(new FileReader(finishFile))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split("[ ]+");
                Runner r = findRunner(Integer.parseInt(parts[0]));
                r.setFinishTime(parts[1]);
            }
        }
    }

    private void loadFinishBinary(String finishFilePath) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        finishFilePath = Character.toUpperCase(finishFilePath.charAt(0)) + finishFilePath.substring(1);
        bw.createFinish(finishFilePath);
        File finishFile = new File(Writer.dataDirectory,finishFilePath);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(finishFile))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int number = dis.readInt();
                    long finishTimeNano = dis.readLong();
                    String finishTime = (LocalTime.ofNanoOfDay(finishTimeNano)).format(Runner.dtfFinish);
                    Runner r = findRunner(number);
                    r.setFinishTime(finishTime);
                } catch (EOFException ex) {
                    isEnd = true;
                }
            }
        }

    }

}
