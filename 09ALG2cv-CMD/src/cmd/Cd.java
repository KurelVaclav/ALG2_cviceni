package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class Cd extends Command {

    @Override
    public Status execute(File actualDir) {
        File file;
        if (params.length == 1) {
            return new Status(actualDir, "Enter the parameter\n");
        }
        if (params.length > 2) {
            return new Status(actualDir, "Invalid command\n");
        }
        if (params[1].equals("..")) {
            file = new File(actualDir.getParent());
        } else {
            try {
                file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            } catch (Exception e) {
                throw new RuntimeException("This file does not exist");
            }
        }
        return new Status(file, "");
    }

}
