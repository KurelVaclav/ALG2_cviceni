package ui;

import app.Employee;
import java.util.Scanner;
import app.PayrollEditor;
import app.Tax;
import app.Wage;
import java.io.FileNotFoundException;
import java.io.IOException;
import utils.PayrollInterface;

/**
 * UI
 *
 * @author Václav Kurel
 */
public class payrollSystemUI {

    public static Scanner sc = new Scanner(System.in);
    public static PayrollInterface pay;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        pay = new PayrollEditor();
        boolean isEnd = false;
        int option;
        while (!isEnd) {
            menu();
            option = sc.nextInt();
            switch (option) {
                case 0:
                    isEnd = true;
                    break;
                case 1:
                    loadEmployee(pay);
                    break;
                case 2:
                    loadHours(pay);
                    break;
                case 3:
                    Employee e = addEmployee();
                    pay.addEmployeeToList(e);
                    System.out.println(pay.getEmployeesInfo());
                    break;
                case 4:
                    Wage wage = addWage();
                    pay.addWageToList(wage);
                    System.out.println(pay.getWagesInfo());
                    break;
                case 5:
                    System.out.println("Zadejte název výstupního souboru: ");
                    String resultFile = sc.next();
                    pay.saveWages(resultFile);
                    break;
                default:
                    System.out.println("Neplatná volba!");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("****************************");
        System.out.println("PayrollSystem");
        System.out.println("Zvolte volbu: ");
        System.out.println("1 - načtení souboru se zaměstnanci");
        System.out.println("2 - načtení souboru s odpracovanými hodinami");
        System.out.println("3 - přidání zaměstnance");
        System.out.println("4 - přidání odpracovaných hodin zaměstnanci");
        System.out.println("5 - uložení výsledků");
        System.out.println("0 - ukončení programu");
        System.out.println("****************************");
    }

    public static void loadEmployee(PayrollInterface pay) throws FileNotFoundException {
        System.out.println("Zadejte název souboru se zaměstnanci: ");
        String employeeFile = sc.next();
        pay.loadEmployees(employeeFile);
        System.out.println(pay.getEmployeesInfo());
    }

    public static void loadHours(PayrollInterface pay) throws FileNotFoundException {
        System.out.println("Zadejte název souboru hodinových mezd: ");
        String wagesFile = sc.next();
        pay.loadHours(wagesFile);
        System.out.println(pay.getWagesInfo());
    }

    public static Employee addEmployee() {
        System.out.println("Zadejte ID: ");
        int id = sc.nextInt();
        System.out.println("Zadejte jméno: ");
        String firstName = sc.next();
        System.out.println("Zadejte příjmení: ");
        String lastName = sc.next();
        System.out.println("Zadejte rok narození: ");
        int year = sc.nextInt();
        System.out.println("Zadejte měsíc narození: ");
        int month = sc.nextInt();
        System.out.println("Zadejte den narození: ");
        int day = sc.nextInt();
        System.out.println("Zadejte národnost: ");
        String nationality = sc.next();
        System.out.println("Zadejte hodinovou sazbu: ");
        double hourTax = sc.nextDouble();
        System.out.println("Zadejte název práce: ");
        String positionName = sc.next();
        Tax tax = new Tax(hourTax, positionName);
        return new Employee(id, firstName, lastName, year, month, day, nationality, tax);
    }

    public static Wage addWage() {
        System.out.println("Zadejte ID: ");
        int id = sc.nextInt();
        System.out.println("Zadejte počet odpracovaných hodin: ");
        int hours = sc.nextInt();
        Employee e = pay.findEmployee(id);
        Wage wage = new Wage(e, hours);
        wage.setGrossWage(hours, e);
        wage.setSuperGrossWage();
        wage.setDownPayment();
        wage.setSHInsurancePayment();
        wage.setSHInsurancePayment();
        wage.setNetWage();
        return wage;
    }

}
