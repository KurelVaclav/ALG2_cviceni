package filehandling;

import app.Employee;
import app.Wage;
import java.io.IOException;
import java.util.List;

/**
 * Abstraktní třída zapisovač
 *
 * @author Václav Kurel
 */
public abstract class Writer {

    public abstract void saveResults(String resultFile, List<Wage> wages) throws IOException;

    public abstract void saveUpdateEmployees(String employeesFile, List<Employee> employees) throws IOException;

    public abstract void saveUpdateHours(String wagesFile, List<Wage> wages) throws IOException;
}