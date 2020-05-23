package app;

import filehandling.TextWriter;
import filehandling.Writer;
import utils.PayrollInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        File eFile = new File(Writer.dataDirectory, employeeFile);
        try (Scanner inData = new Scanner(eFile)) {
            while (inData.hasNext()) {
                int id = inData.nextInt();
                String firstName = inData.next();
                String lastName = inData.next();
                int day = inData.nextInt();
                int month = inData.nextInt();
                int year = inData.nextInt();
                String nationality = inData.next();
                double hourTax = inData.nextDouble();
                String positionName = inData.next();
                Tax tax = new Tax(hourTax, positionName);
                Employee e = new Employee(id, firstName, lastName, day, month, year, nationality, tax);
                employees.add(e);
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

    @Override
    public String getEmployeesInfoSortedByID() {
        Collections.sort(employees);
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice"));
        for (Employee employee : employees) {
            sb.append(employee).append("\n");
        }
        return sb.toString();
    }

    public String getEmployeesInfoSortedByLastName() {
        Collections.sort(employees, Employee.lastNameComparator);
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice"));
        for (Employee employee : employees) {
            sb.append(employee).append("\n");
        }
        return sb.toString();
    }

    public String getEmployeesInfoSortedByFirstName() {
        Collections.sort(employees, Employee.firstNameComparator);
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice"));
        for (Employee employee : employees) {
            sb.append(employee).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        throw new NoSuchElementException("Takový zaměstnanec s ID: " + id + " není evidován!");
    }

    @Override
    public void addEmployeeToList(Employee e) {
        employees.add(e);
    }

    @Override
    public void saveAddedEmployees(String employeeFile) throws IOException {
        Writer w;
        w = new TextWriter();
        w.saveUpdateEmployees(employeeFile, employees);
    }

    @Override
    public void loadHours(String wagesFile) throws FileNotFoundException {
        File wFile = new File(Writer.dataDirectory, wagesFile);
        try (Scanner inData = new Scanner(wFile)) {
            int id, hours;
            while (inData.hasNext()) {
                id = inData.nextInt();
                Employee e = findEmployee(id);
                hours = inData.nextInt();
                Wage w = new Wage(e, hours);
                wages.add(w);
            }
        }
    }

    @Override
    public String getHoursInfo() {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%-10s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice", "hodiny"));
        for (Wage wage : wages) {
            sb.append(wage.toHourString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void calculateWages() {
        for (Wage wage : wages) {
            int hours = wage.getHours();
            Employee e = wage.getEmployee();
            wage.setGrossWage(hours, e);
            wage.setSuperGrossWage();
            wage.setDownPayment();
            wage.setSHInsurancePayment();
            wage.setNetWage();
        }
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
        Collections.sort(employees);
        Writer w;
        w = new TextWriter();
        w.saveResults(resultFile, wages);
    }

    @Override
    public void addWageToList(Wage wage) {
        wages.add(wage);
    }

    @Override
    public void saveAddedHours(String wagesFile) throws IOException {
        Writer w;
        w = new TextWriter();
        w.saveUpdateHours(wagesFile, wages);
    }

}
