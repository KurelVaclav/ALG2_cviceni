package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class CmdEditor implements CmdInterface {

    //data
    private boolean isRunning;
    private File actualDir; //naimplementovat java.io.File
    private Command command; //to už je naše třída co potřebujeme

    //konstruktor - ideál bez vstupu
    public CmdEditor() {
        isRunning = true;
        actualDir = new File(System.getProperty("user.dir"));

    }

    //metody z interfacu
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String getActualDir() {
        return actualDir.getAbsolutePath(); //vrátí celou cestu     
    }

    @Override
    public String parseAndExecute(String line) {
//        isRunning = false;  takto jsme to testovali
//        return "Ahoj";
        //parse
        command = Parser.parse(line);
        if (command == null) {
            return "";
        }
        //execute
        Status status = command.execute(actualDir);
        actualDir = status.getActualDir();
        if (actualDir == null) {
            isRunning = false;
        }
        return status.getMessage();
    }

}
