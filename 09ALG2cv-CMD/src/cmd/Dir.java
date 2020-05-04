package cmd;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Václav Kurel
 */
public class Dir extends Command {

    @Override
    public Status execute(File actualDir) {
        File[] files;
        switch (params.length) {
            case 1:
                // => obyčený dir
                files = actualDir.listFiles();
                return new Status(actualDir, dirToString(files));
            case 2:
                if (params[1].equals("-o")) {
                    files = actualDir.listFiles();
                    Arrays.sort(files);
                    return new Status(actualDir, dirToString(files));
                } else {
                    return new Status(actualDir, "Invalid command\n");
                }
            case 3:
            switch (params[1]) {
                case "-e":
                {
                    String extension = params[2];
                    FileFilter filefilter = (File pathname) -> pathname.getName().endsWith(extension);
                    files = actualDir.listFiles(filefilter);
                    return new Status(actualDir, dirToString(files));
                }
                case "-s":
                {
                    int size = Integer.parseInt(params[2]);
                    FileFilter filefilter = (File pathname) -> pathname.length() > size;
                    files = actualDir.listFiles(filefilter);
                    return new Status(actualDir, dirToString(files));
                }
                default:
                    return new Status(actualDir, "Invalid command\n");
            }

            default:
                return new Status(actualDir, "Invalid command\n");
        }
    }

    private String dirToString(File[] files) {
        StringBuilder sb = new StringBuilder("");
        for (File file : files) {
            if (file.isDirectory()) {
                sb.append(String.format("%s%n", file.getName()));
            } else {
                sb.append(String.format("%-20s%6d", file.getName(), file.length()));
                sb.append(new Date(file.lastModified()) + "\n");
            }
        }
        return sb.toString();
    }

}
