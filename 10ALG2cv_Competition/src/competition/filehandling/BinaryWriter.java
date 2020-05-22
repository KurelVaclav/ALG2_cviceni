package competition.filehandling;

import competition.app.Runner;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class BinaryWriter extends Writer {

    // v binární souboru nejsou oddělovací znaky a všechno je jeden za druhým -> předdefinovat počet bytů
    @Override
    public void saveResults(String resultFilePath, List<Runner> runners) throws IOException {
        File resultFile = new File(dataDirectory, resultFilePath);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(resultFile))) {
            dos.writeUTF("Nové výsledky");
            int n = 1;
            for (Runner runner : runners) {
                dos.writeInt(n);
                //dos.writeChar('.');
                dos.writeUTF(runner.getFirstName());
                int nCharsLN = runner.getLastName().length();
                dos.writeInt(nCharsLN); //počet znaků příjmení
                for (int i = 0; i < nCharsLN; i++) {
                    dos.writeChar(runner.getLastName().charAt(i));
                }
                dos.writeLong(runner.runningTime().toNanoOfDay());
                n++;
            }
        }
    }

    public void createStart(String startFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(dataDirectory,startFilePath);
        File textFile = new File(dataDirectory,startFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    in.writeUTF(out.next());
                    in.writeUTF(out.next());
                    String starTime = out.next();
                    LocalTime start = LocalTime.parse(starTime, Runner.dtfStart);
                    in.writeLong(start.toNanoOfDay());
                }
            }
        }
    }

    public void createFinish(String finishFilePath) throws FileNotFoundException, IOException {
        File binaryFile = new File(dataDirectory,finishFilePath);
        File textFile = new File(dataDirectory,finishFilePath.replace(".dat", ".txt"));
        try (Scanner out = new Scanner(textFile)) {
            try (DataOutputStream in = new DataOutputStream(new FileOutputStream(binaryFile))) {
                while (out.hasNext()) {
                    in.writeInt(out.nextInt());
                    String finishTime = out.next();
                    LocalTime finish = LocalTime.parse(finishTime, Runner.dtfFinish);
                    in.writeLong(finish.toNanoOfDay());
                }
            }
        }
    }

}
