package app;

import filehandling.TextWriter;
import filehandling.Writer;
import utils.PayrollInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Václav Kurel
 */
public class PayrollEditor implements PayrollInterface {
    
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Wage> wages = new ArrayList<>();

    /**
     *
     * @param employeeFile
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void loadEmployees(String employeeFile) throws FileNotFoundException {
        File eFile = new File(employeeFile);
        try (Scanner inData = new Scanner(eFile)) {
            int id, day, month, year, hourTax;
            String firstName, lastName, placeOfBirth, positionName;
            while (inData.hasNext()) {
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
            }
        }
    }
    
    @Override
    public void loadHours(String wagesFile) throws FileNotFoundException {
        File wFile = new File(wagesFile);
        try (Scanner inData = new Scanner(wFile)) {
            int id, hours;
            while (inData.hasNext()) {
                id = inData.nextInt();
                Employee e = findEmployee(id);
                hours = inData.nextInt();
                Wage w = new Wage(e, hours);
                w.setGrossWage(hours, e);
                w.setSuperGrossWage();
                w.setDownPayment();
                w.setSHInsurancePayment();
                w.setNetWage();
                wages.add(w);
            }
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
        throw new NoSuchElementException("Takový zaměstnanec s ID: " + id + " nepracoval!");
    }
    
    @Override
    public String getWagesInfo() {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%-10s%-10s%-10s%-10s%-10s%-10s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice", "hodiny", "HM", "SHM", "ZnD", "OSZ", "CM"));
        for (Wage wage : wages) {
            sb.append(wage).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public void saveWages(String resultFile) throws IOException {
        Writer w;
        w = new TextWriter();
        w.saveResults(resultFile, wages);
    }
    
}
