package utils;

import java.io.FileNotFoundException;

/**
 *
 * @author Václav Kurel
 */
public interface PayrollInterface {

    public void loadEmployees(String employeeFile) throws FileNotFoundException;

    public void loadHours(String wagesFile) throws FileNotFoundException;

    public String getEmployeesInfo();

    public String getWagesInfo();

}
