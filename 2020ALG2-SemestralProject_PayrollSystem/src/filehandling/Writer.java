package filehandling;

import app.Wage;
import java.io.IOException;
import java.util.List;

/**
 * Abstraktní třída zapisovač
 * @author Václav Kurel
 */
public abstract class Writer {

    public abstract void saveResults(String resultFile, List<Wage> wages) throws IOException;
}
