package filehandling;

import app.Wage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Václav Kurel
 */
public class TextWriter extends Writer{

    @Override
    public void saveResults(String resultFile, List<Wage> wages) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFile, true)))){
            pw.println("Mzda ze dne: "+ LocalDate.now());
            pw.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%-10s%-10s%-10s%-10s%-10s%-10s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice", "hodiny", "HM", "SHM", "ZnD", "OSZ", "CM");
            for (Wage wage : wages) {
                pw.println(wage.toString());
            }
        }
    }
    
}
