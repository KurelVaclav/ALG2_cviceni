package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class Status {
    private File actualDir;
    private String message;

    public Status(File actualDir, String message) {
        this.actualDir = actualDir;
        this.message = message;
    }

    
    
    public File getActualDir() {
        return actualDir;
    }

    public String getMessage() {
        return message;
    }
    
}
