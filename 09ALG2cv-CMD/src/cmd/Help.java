package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class Help extends Command {

    @Override
    public Status execute(File actualDir) {
        //\n nový řádek; %- zarovná vlevo
        String help = "HELP\n"
                + String.format("%-40s %s\n", "help", "Display help")
                + String.format("%-40s %s\n", "dir", "Display list of files and folders")
                + String.format("%-40s %s\n", "dir [-o]", "Display orded list of files and folders")
                + String.format("%-40s %s\n", "dir [-e] [file extension]", "Display list of files and folders with specified extension")
                + String.format("%-40s %s\n", "dir [-s] [size]", "Display list of files and folders bigger than a specified size")
                + String.format("%-40s %s\n", "cd ..", "Change directory - move to the folder one level higher")
                + String.format("%-40s %s\n", "cd [folder name]", "Change directory - move to a specific folder")
                + String.format("%-40s %s\n", "mkdir [folder name]", "Create new folder")
                + String.format("%-40s %s\n", "rename [name From] [name To]", "Rename file or folder")
                + String.format("%-40s %s\n", "exit", "Finish program cmd");
        return new Status(actualDir, help);
    }

}
