package cmd;

import java.util.Scanner;

/**
 * začít návrh od Cmd_UI rozhraní (uživatelské) - oddělit pak třídy implementace
 * - neobsahují žádné výpisy sout, pouze logika
 *
 * @author Václav Kurel
 */
public class Cmd_UI {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CmdInterface cmd = new CmdEditor();
        String line;
        while (cmd.isRunning()) {
            System.out.print(cmd.getActualDir() + "$ ");
            line = sc.nextLine();
            try {
                System.out.println(cmd.parseAndExecute(line));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
