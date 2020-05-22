package competition.utils;

import competition.app.Runner;
import competition.filehandling.Writer;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Třída pro čtení z binárního souboru
 *
 * @author Václav Kurel
 */
public class ReadResult {

    public static void main(String[] args) {
        try {
            readResult("result.dat");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readResult(String resutFilePath) throws FileNotFoundException, IOException {
        File resultFile = new File(Writer.dataDirectory,resutFilePath);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(resultFile))) {
            System.out.println(dis.readUTF());
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    System.out.print(dis.readInt() + ". ");
                    String firstName = dis.readUTF();
                    int nChars = dis.readInt();
                    String lastName = "";
                    for (int i = 0; i < nChars; i++) {
                        lastName += dis.readChar();
                    }
                    LocalTime runningTime = LocalTime.ofNanoOfDay(dis.readLong());
                    System.out.println(firstName + " " + lastName + " " + runningTime.format(Runner.dtfFinish));
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
    }
}
