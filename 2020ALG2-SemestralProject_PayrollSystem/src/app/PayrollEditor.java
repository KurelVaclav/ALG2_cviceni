package app;

import filehandling.BinaryWriter;
import filehandling.ExcelWiter;
import filehandling.TextWriter;
import filehandling.Writer;
import java.io.DataInputStream;
import java.io.EOFException;
import utils.PayrollInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.activation.UnsupportedDataTypeException;

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
    public void loadEmployees(String employeeFile) throws FileNotFoundException, IOException {
        if (employeeFile.endsWith(".txt")) {
            loadEmployeesTxt(employeeFile);
        } else if (employeeFile.endsWith(".dat")) {
            loadEmployeesBinary(employeeFile);
        } else {
            throw new UnsupportedDataTypeException("Nepodporovaný formát");
        }
    }

    public void loadEmployeesTxt(String employeeFile) throws FileNotFoundException {
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

    public void loadEmployeesBinary(String employeeFile) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        employeeFile = Character.toUpperCase(employeeFile.charAt(0)) + employeeFile.substring(1);
        bw.createEmployees(employeeFile);
        File eFile = new File(Writer.dataDirectory, employeeFile);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(eFile))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int id = dis.readInt();
                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    int day = dis.readInt();
                    int month = dis.readInt();
                    int year = dis.readInt();
                    String nationality = dis.readUTF();
                    double houTax = dis.readDouble();
                    String positionName = dis.readUTF();
                    Tax tax = new Tax(houTax, positionName);
                    Employee e = new Employee(id, firstName, lastName, day, month, year, nationality, tax);
                    employees.add(e);
                } catch (EOFException ex) {
                    isEnd = true;
                }
            }
        }
    }

    @Override
    public String getEmployeesInfo() {
        try {
            Collections.sort(employees);
        } catch (Exception e) {
            System.err.println("nepodařilo se seřadit");
        }
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

    @Override
    public String getEmployeesInfoSortedByLastName() {
        Collections.sort(employees, Employee.lastNameComparator);
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%-5s%-20s%-20s%-12s%-6s%-8s%-20s%n", "ID", "jméno", "příjmění", "narození", "země", "Kč/hod", "pozice"));
        for (Employee employee : employees) {
            sb.append(employee).append("\n");
        }
        return sb.toString();
    }

    @Override
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
        if (e != null) {
            employees.add(e);
        }
    }

    @Override
    public void saveAddedEmployees(String employeeFile) throws IOException {
        TextWriter w;
        w = new TextWriter();
        w.saveUpdateEmployees(employeeFile, employees);
    }

    @Override
    public void loadHours(String wagesFile) throws FileNotFoundException, IOException {
        if (wagesFile.endsWith(".txt")) {
            loadHoursTxt(wagesFile);
        } else if (wagesFile.endsWith(".dat")) {
            loadHoursBinary(wagesFile);
        } else {
            throw new UnsupportedDataTypeException("Nepodporovaný formát");
        }
    }

    public void loadHoursTxt(String wagesFile) throws FileNotFoundException {
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

    public void loadHoursBinary(String wagesFile) throws IOException {
        BinaryWriter bw = new BinaryWriter();
        wagesFile = Character.toUpperCase(wagesFile.charAt(0)) + wagesFile.substring(1);
        bw.createHours(wagesFile);
        File wFile = new File(Writer.dataDirectory, wagesFile);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(wFile))) {
            boolean isEnd = false;
            while (!isEnd) {
                try {
                    int id = dis.readInt();
                    Employee e = findEmployee(id);
                    int hours = dis.readInt();
                    Wage w = new Wage(e, hours);
                    wages.add(w);
                } catch (EOFException ex) {
                    isEnd = true;
                }
            }
        }

    }

    @Override
    public String getHoursInfo() {
        Collections.sort(wages, Wage.idComparator);
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
        Collections.sort(wages, Wage.idComparator);
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
        if (resultFile.endsWith(".txt")) {
            w = new TextWriter();
        } else if (resultFile.endsWith(".dat")) {
            w = new BinaryWriter();
        } else if (resultFile.endsWith(".xlsx")) {
            w = new ExcelWiter();
        } else {
            throw new IllegalArgumentException("Taková koncovka souboru není podporována");
        }
        w.saveResults(resultFile, wages);

    }

    @Override
    public void addWageToList(Wage wage) {
        if (wage != null) {
            wages.add(wage);
        }
    }

    @Override
    public void saveAddedHours(String wagesFile) throws IOException {
        TextWriter w;
        w = new TextWriter();
        w.saveUpdateHours(wagesFile, wages);
    }

    @Override
    public void saveResultToExcel(String resultFile) throws IOException {
        Writer w = new ExcelWiter();
        w.saveResults(resultFile, wages);
    }

}
