package app;

import java.time.LocalDate;

/**
 * Třída reprezentující zaměstnance
 * data: id = identifikační číslo, jméno, příjmení, datum narození, národnost, taxa - jaká pozice mu byla přidělena
 * 
 * @author Václav Kurel
 */
public class Employee implements Comparable<Employee> {

    //data
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private Tax tax;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param year
     * @param month
     * @param day
     * @param nationality
     * @param tax
     */
    public Employee(int id, String firstName, String lastName, int year, int month, int day, String nationality, Tax tax) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = java.time.LocalDate.of(year, month, day);
        this.nationality = nationality;
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public Tax getTax() {
        return tax;
    }

    public String getEmployeeToString() {
        return String.format("%-5d%-20s%-20s%-12s%-6s", id, firstName, lastName, dateOfBirth.toString(), nationality);
    }

    @Override
    public String toString() {
        return getEmployeeToString() + tax.getTaxesToString();
    }

    @Override
    public int compareTo(Employee o) {
        return this.id - o.id;
    }

//    public static void main(String[] args) {
//        Tax vaclav = new Tax(250, "IT-spravce");
//        Employee e = new Employee(1, "Václav", "Kurel", 1997, 05, 21, "CZ", vaclav);
//        System.out.println(e);
//    }
}
