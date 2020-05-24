package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class DirWithSubdir extends Command {

    @Override
    public Status execute(File actualDir) {
        return new Status(actualDir, dirWithSubdir(actualDir, 0));
    }

    private String dirWithSubdir(File parent, int i) {
        String dirs = "";
        File[] files = parent.listFiles();
        for (File file : files) {
            for (int j = 0; j < i; j++) {
                dirs = dirs + "-";
            }
            dirs = dirs + ("-" + file.getName() + "\n");
            if (file.isDirectory()) {
                dirs = dirs + dirWithSubdir(file, i + 1);
            }
        }
        return dirs;
    }

}
