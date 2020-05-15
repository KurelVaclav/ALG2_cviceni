package app;

import java.io.BufferedReader;
import utils.PayrollInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Václav Kurel
 */
public class PayrollEditor implements PayrollInterface {

    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public String parseAndMakeInfo(String[] employeeInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmpoyeeInfo(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax calculate() {
        return null;
    }

    @Override
    public void load(String employeeFile, String wagesFile) {
        File eFile = new File(employeeFile);
        try {
            Scanner inData = new Scanner(eFile);
            int id, day, month, year, hourTax;
            String firstName, lastName, placeOfBirth, positionName;
            while (inData.hasNext()) {
                try {
                    id = inData.nextInt();
                    firstName = inData.next();
                    lastName = inData.next();
                    day = inData.nextInt();
                    month = inData.nextInt();
                    year = inData.nextInt();
                    placeOfBirth = inData.next();
                    hourTax = inData.nextInt();
                    positionName = inData.next();
                    Tax tax = new Tax(hourTax, positionName);
                    Employee e = new Employee(id, firstName, lastName, year, month, day, placeOfBirth, tax);
                    employees.add(e);
                } catch (Exception e) {
                    throw new RuntimeException("Nepodařilo se naparsovat!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor nebyl nalezen!");
        }

        File wFile = new File(wagesFile);
        try {
            BufferedReader hourData = new BufferedReader(new FileReader(wFile));
            String line;
            try {
                while ((line = hourData.readLine()) != null) {
                    String[] parts = line.split("[ ]+");
                    Employee e = findEmployee(Integer.parseInt(parts[0]));
                    
                }
            } catch (Exception ex) {
                throw new RuntimeException("Nepodařilo se naparsovat!");
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Soubor nebyl nalezen!");
        }

    }

    @Override
    public String getEmployeesInfo() {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice"));
        for (Employee employee : employees) {
            sb.append(employee).append("\n");
        }
        return sb.toString();
    }

    private Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new NoSuchElementException("Takový zaměstnanec nepracoval!");
    }

}
