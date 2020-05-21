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
                    loadEmployee();
                    break;
                case 2:
                    loadHours();
                    break;
                case 3:
                    addEmployeeMenu();
                    break;
                case 4:

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

    public static void loadEmployee() throws FileNotFoundException {
        System.out.println("Zadejte název souboru se zaměstnanci: ");
        String employeeFile = sc.next();
        pay.loadEmployees(employeeFile);
        System.out.println(pay.getEmployeesInfo());
    }

    public static void loadHours() throws FileNotFoundException {
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

    public static void addEmployeeMenu() throws IOException {
        System.out.println("Zadání nových zaměstnanců: ");
        boolean isEnd = false;
        while (!isEnd) {
            Employee e = addEmployee();
            pay.addEmployeeToList(e);
            System.out.println("Přídat dalšího zaměstnance? a/n");
            String choice = sc.next();
            if (choice.toLowerCase().charAt(0) == 'n') {
                isEnd = true;
            }
        }
        System.out.println(pay.getEmployeesInfo());
        System.out.println("Přejete si nové data uložit? a/n");
        String choice = sc.next();
        if (choice.toLowerCase().charAt(0) == 'a') {
            System.out.println("Zadejte název souboru se zaměstnanci: ");
            String employeeFile = sc.next();
            pay.saveAddedEmployees(employeeFile);
        }
    }

    public static Wage addWage() {
        System.out.println("Zadejte ID: ");
        int id = sc.nextInt();
        System.out.println("Zadejte počet odpracovaných hodin: ");
        int hours = sc.nextInt();
        Employee e = pay.findEmployee(id);
        Wage wage = new Wage(e, hours);
//        wage.setGrossWage(hours, e);
//        wage.setSuperGrossWage();
//        wage.setDownPayment();
//        wage.setSHInsurancePayment();
//        wage.setSHInsurancePayment();
//        wage.setNetWage();
        return wage;
    }

    public void addHoursMenu() throws IOException {
        System.out.println("Zadání nové pracovní hodiny: ");
        boolean isEnd = false;
        while (!isEnd) {
            Wage wage = addWage();
            pay.addWageToList(wage);
            System.out.println("Přídat dalšího odpracované hodiny? a/n");
            String choice = sc.next();
            if (choice.toLowerCase().charAt(0) == 'n') {
                isEnd = true;
            }
        }
        System.out.println(pay.getWagesInfo());
        System.out.println("Přejete si nové data uložit? a/n");
        String choice = sc.next();
        if (choice.toLowerCase().charAt(0) == 'a') {
            System.out.println("Zadejte název souboru s odpracovanými hodiny: ");
            String wagesFile = sc.next();
            pay.saveAddedHours(wagesFile);
        }
    }

}
