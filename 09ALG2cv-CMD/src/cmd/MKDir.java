package cmd;

import java.io.File;

/**
 *
 * @author Václav Kurel
 */
public class MKDir extends Command {

    @Override
    public Status execute(File actualDir) {
        if(params.length == 2){
            File file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            file.mkdir();
            return new Status(actualDir, "The file has been created\n");
        }else{
            return new Status(actualDir, "Invalid command\n");
        }
        
    }

}
