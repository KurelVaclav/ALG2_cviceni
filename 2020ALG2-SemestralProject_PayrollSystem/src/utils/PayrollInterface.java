package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Václav Kurel
 */
public interface PayrollInterface {

    public void loadEmployees(String employeeFile) throws FileNotFoundException;

    public void loadHours(String wagesFile) throws FileNotFoundException;

    public String getEmployeesInfo();

    public String getWagesInfo();

    public void saveWages(String resultFile)throws IOException;

}
