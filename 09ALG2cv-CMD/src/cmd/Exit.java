package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class Exit extends Command {

    @Override
    public Status execute(File actualDir) {
        return new Status(null, "End\n");
    }

}
