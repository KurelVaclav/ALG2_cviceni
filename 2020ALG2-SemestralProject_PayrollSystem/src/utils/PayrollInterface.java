package utils;

import app.Employee;
import app.Wage;
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

    public Employee findEmployee(int id);

    public void saveWages(String resultFile) throws IOException;

    public void addEmployeeToList(Employee e);

    public void addWageToList(Wage wage);

    public void saveAddedEmployees(String employeeFile) throws IOException;

    public void saveAddedHours(String wagesFile) throws IOException;

    public String getHoursInfo();

    public void calculateWages();

    public String getEmployeesInfoSortedByID();

    public String getEmployeesInfoSortedByLastName();

    public String getEmployeesInfoSortedByFirstName();

}
