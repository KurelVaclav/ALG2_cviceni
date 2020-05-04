package cmd;

/**
 * metody pro implementaci, vznikly že jsem si řekl, co bude v UI
 * @author Václav Kurel
 */
public interface CmdInterface {

    public boolean isRunning();

    public String getActualDir();

    public String parseAndExecute(String line);

}
