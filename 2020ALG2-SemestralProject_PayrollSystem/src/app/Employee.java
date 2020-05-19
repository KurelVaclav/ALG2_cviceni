package app;

import java.time.LocalDate;

/**
 *
 * @author Václav Kurel
 */
public class Employee implements Comparable<Employee> {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private Tax tax;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param year
     * @param month
     * @param day
     * @param placeOfBirth
     * @param tax
     */
    public Employee(int id, String firstName, String lastName, int year, int month, int day, String placeOfBirth, Tax tax) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = java.time.LocalDate.of(year, month, day);
        this.placeOfBirth = placeOfBirth;
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public Tax getTax() {
        return tax;
    }

    public String getEmployeeToString() {
        return String.format("%-5d%-20s%-20s%-12s%-6s", id, firstName, lastName, dateOfBirth.toString(), placeOfBirth);
    }

    @Override
    public String toString() {
        return getEmployeeToString() + tax.getTaxesToString();
    }

    @Override
    public int compareTo(Employee o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public static void main(String[] args) {
//        Tax vaclav = new Tax(250, "IT-spravce");
//        Employee e = new Employee(1, "Václav", "Kurel", 1997, 05, 21, "CZ", vaclav);
//        System.out.println(e);
//    }

}
