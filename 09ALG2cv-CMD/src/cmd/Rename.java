package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class Rename extends Command {

    @Override
    public Status execute(File actualDir) {
        if (params.length == 3) {
            File file1 = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            File file2 = new File(actualDir.getAbsolutePath() + "\\" + params[2]);
            return new Status(actualDir, "The file has been renamed\n");
        } else {
            return new Status(actualDir, "Invalid command\n");
        }
    }

}
