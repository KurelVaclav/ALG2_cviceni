package utils;

import app.Tax;

/**
 *
 * @author Václav Kurel
 */
public interface PayrollInterface {
    
    public String parseAndMakeInfo(String[] employeeInfo);

    public String getEmpoyeeInfo(int ID);

    public Tax calculate();

    public void load(String employeeFile, String wagesFile);

    public String getEmployeesInfo();

}
