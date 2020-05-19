package competition.filehandling;

import competition.app.Runner;
import competition.filehandling.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * potomek Writera, chci aby dědil
 *
 * @author Václav Kurel
 */
public class TextWriter extends Writer {

    @Override
    public void saveResults(String resultFilePath, List<Runner> runners) throws IOException {//IOE ošetřit i u Writera
        //if neexistuje soubor, tak PW ho vytvoří, když existuje tak do něj zapíše
        //FileWriter si bere i rovnou String
        //když dám tru, tak to nepřepisuje //zavřít printWriter
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFilePath, true)))) {
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(Syste.out, "Cp1250"),true);//nastavení kodování
            pw.println("Nové výsledky: ");
            int n = 1; //pořadí běžců
            for (Runner runner : runners) {
                pw.print(n + ". "); //oddělené mezerou
                pw.println(runner.toString());
                n++;
            }
        }
    }

    //před try with resourses
//        public void saveResults(String resultFilePath, List<Runner> runners) throws IOException { //IOE ošetřit i u Writera
//        PrintWriter pw = null; //if neexistuje soubor, tak PW ho vytvoří, když existuje tak do něj zapíše
//        try {
//            pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFilePath))); //FileWriter si bere i rovnou String
//            pw.println("Nové výsledky: ");
//            int n = 1; //pořadí běžců
//            for (Runner runner : runners) {
//                pw.print(n + ". "); //oddělené mezerou
//                pw.println(runner.toString());
//                n++;
//            }
//        } finally { //zavřít printWriter
//            if (pw != null) {
//                pw.close();
//            }
//        }
//    }
}
