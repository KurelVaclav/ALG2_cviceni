package ui;

import app.Employee;
import java.util.Scanner;
import app.PayrollEditor;
import app.Tax;
import app.Wage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.IllegalInputFromUser;
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
     */
    public static void main(String[] args) {
        pay = new PayrollEditor();
        boolean isEnd = false;
        int choice;
        String answer;
        while (!isEnd) {
            menu();
            answer = sc.next();
            try {
                choice = Integer.parseInt(answer);
                switch (choice) {
                    case 0:
                        isEnd = true;
                        break;
                    case 1:
                        employeeMenu();
                        break;
                    case 2:
                        wageMenu();
                        break;
                    default:
                        System.out.println(new IllegalInputFromUser("Nevalidní vstup"));
                }
            } catch (NumberFormatException e) {
                System.out.println(new IllegalInputFromUser("Nevalidní vstup"));
            }
        }
    }

    public static void menu() {
        System.out.println("**********************************");
        System.out.println("PayrollSystem");
        System.out.println("Vyberte možnost(musí být celé kladné číslo: ");
        System.out.println("1 - správa seznamu zaměstnanců");
        System.out.println("2 - mzdový portál");
        System.out.println("0 - ukončení programu");
        System.out.println("**********************************");
    }

    public static void employeeMenu() {
        System.out.println("Správa seznamu zaměstnanců: ");
        try {
            loadEmployee();
            int choice;
            String answer;
            boolean isEnd = false;
            while (!isEnd) {
                System.out.println("Vyberte možnost(musí být celé číslo): ");
                System.out.println("1 - přidání nového zaměstnance");
                System.out.println("2 - výpis seznamu zaměstnanců");
                System.out.println("3 - najít zaměstnance dle ID");
                System.out.println("0 - ukončení správy seznamu zaměstnanců");
                answer = sc.next();
                try {
                    choice = Integer.parseInt(answer);
                    switch (choice) {
                        case 1:
                            addEmployeeMenu();
                            break;
                        case 2:
                            System.out.println("Seřadit dle (vyberte možnost): ");
                            System.out.println("1 - ID");
                            System.out.println("2 - Jména");
                            System.out.println("3 - Příjmení");
                            System.out.println("0 - Neseřazený seznam");
                            String reply = sc.next();
                            try {
                                int option = Integer.parseInt(reply);
                                switch (option) {
                                    case 0:
                                        System.out.println(pay.getEmployeesInfo());
                                        break;
                                    case 1:
                                        System.out.println(pay.getEmployeesInfoSortedByID());
                                        break;
                                    case 2:
                                        System.out.println(pay.getEmployeesInfoSortedByFirstName());
                                        break;
                                    case 3:
                                        System.out.println(pay.getEmployeesInfoSortedByLastName());
                                        break;
                                    default:
                                        System.out.println(new IllegalInputFromUser("Nevalidní vstup"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(new IllegalInputFromUser("Nevalidní vstup"));
                            }
                            break;

                        case 3:
                            boolean search = false;
                            while (!search) {
                                try {
                                    System.out.println("Zadejte ID zaměstnance");
                                    int id = sc.nextInt();
                                    Employee e = pay.findEmployee(id);
                                    System.out.println(e.toString());
                                    System.out.println("Najít dalšího zaměstnance? a/n");
                                    String repl = sc.next();
                                    search = repl.toLowerCase().charAt(0) == 'n';
                                } catch (NoSuchElementException e) {
                                    System.out.println(e);
                                }
                            }

                        case 0:
                            isEnd = true;
                            break;
                        default:
                            System.out.println(new IllegalInputFromUser("nevalidní vstup"));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(new IllegalInputFromUser("nevalidní vstup"));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Soubor nebyl nalezen");
        }
    }

    public static void loadEmployee() throws FileNotFoundException {
        System.out.println("Zadejte název souboru se zaměstnanci: ");
        String employeeFile = sc.next();
        pay.loadEmployees(employeeFile);
        System.out.println(pay.getEmployeesInfo());
    }

    /**
     *
     * @return
     */
    public static Employee addEmployee() {
        System.out.println("Zadejte ID: ");
        int id = sc.nextInt();
        System.out.println("Zadejte jméno: ");
        String firstName = sc.next();
        System.out.println("Zadejte příjmení: ");
        String lastName = sc.next();
        System.out.println("Zadejte den narození: ");
        int day = sc.nextInt();
        System.out.println("Zadejte měsíc narození: ");
        int month = sc.nextInt();
        System.out.println("Zadejte rok narození: ");
        int year = sc.nextInt();
        System.out.println("Zadejte národnost: ");
        String nationality = sc.next();
        System.out.println("Zadejte hodinovou sazbu: ");
        double hourTax = sc.nextDouble();
        System.out.println("Zadejte název práce: ");
        String positionName = sc.next();
        Tax tax = new Tax(hourTax, positionName);
        return new Employee(id, firstName, lastName, day, month, year, nationality, tax);
    }

    public static void addEmployeeMenu() {
        System.out.println("Zadání nových zaměstnanců: ");
        boolean isEnd = false;
        while (!isEnd) {
            Employee e;
            e = addEmployee();
            pay.addEmployeeToList(e);
            System.out.println("Přídat dalšího zaměstnance? a/n");
            String choice = sc.next();
            isEnd = choice.toLowerCase().charAt(0) == 'n';
        }
        System.out.println(pay.getEmployeesInfo());
        System.out.println("Přejete si nové data uložit? a/n");
        String choice = sc.next();
        if (choice.toLowerCase().charAt(0) == 'a') {
            System.out.println("Zadejte název souboru se zaměstnanci: ");
            String employeeFile = sc.next();
            try {
                pay.saveAddedEmployees(employeeFile);
            } catch (IOException ex) {
                System.out.println("Soubor se nepodařilo uložit");
            }
        }
    }

    public static void wageMenu() {
        System.out.println("Mzdový portál: ");
        try {
            loadHours();
            int choice;
            String answer;
            boolean isEnd = false;
            while (!isEnd) {
                System.out.println("Vyberte možnost(musí být celé číslo): ");
                System.out.println("1 - přidání odpracovaných hodin zaměstnanci");
                System.out.println("2 - výpis seznamu zaměstnanců s odpracovanými hodinami");
                System.out.println("3 - výpočet mezd");
                System.out.println("0 - ukončení správy seznamu zaměstnanců");
                answer = sc.next();
                try {
                    choice = Integer.parseInt(answer);
                    switch (choice) {
                        case 0:
                            isEnd = true;
                            break;
                        case 1:
                            addHoursMenu();
                            break;
                        case 2:
                            System.out.println(pay.getHoursInfo());
                            break;
                        case 3:
                            calculateWage();
                            break;
                        default:
                            System.out.println("nevalidní vstup");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nevalidní vstup");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Soubor nebyl nalezen");
        }
    }

    public static void loadHours() throws FileNotFoundException {
        System.out.println("Zadejte název souboru hodinových mezd: ");
        String wagesFile = sc.next();
        pay.loadHours(wagesFile);
        System.out.println(pay.getHoursInfo());
    }

    public static Wage addHours() {
        System.out.println("Zadejte ID: ");
        int id = sc.nextInt();
        System.out.println("Zadejte počet odpracovaných hodin: ");
        int hours = sc.nextInt();
        Employee e = pay.findEmployee(id);
        Wage wage = new Wage(e, hours);
        return wage;
    }

    public static void addHoursMenu() {
        System.out.println("Zadání nové pracovní hodiny: ");
        boolean isEnd = false;
        while (!isEnd) {
            Wage wage = addHours();
            pay.addWageToList(wage);
            System.out.println("Přídat dalšího odpracované hodiny? a/n");
            String choice = sc.next();
            isEnd = choice.toLowerCase().charAt(0) == 'n';
        }
        System.out.println(pay.getHoursInfo());
        System.out.println("Přejete si nové data uložit? a/n");
        String choice = sc.next();
        if (choice.toLowerCase().charAt(0) == 'a') {
            System.out.println("Zadejte název souboru s odpracovanými hodiny: ");
            String wagesFile = sc.next();
            try {
                pay.saveAddedHours(wagesFile);
            } catch (IOException ex) {
                System.out.println("Soubor se nepodařilo uložit");
            }
        }
    }

    public static void calculateWage() {
        pay.calculateWages();
        System.out.println(pay.getWagesInfo());
        System.out.println("Přejete si nové data uložit? a/n");
        String choice = sc.next();
        if (choice.toLowerCase().charAt(0) == 'a') {
            System.out.println("Zadejte název výstupního souboru: ");
            String resultFile = sc.next();
            try {
                pay.saveWages(resultFile);
            } catch (IOException ex) {
                System.out.println("Soubor se nepodařilo uložit");
            }
        }
    }

//        public static void main(String[] args) throws FileNotFoundException, IOException {
//        pay = new PayrollEditor();
//        boolean isEnd = false;
//        int option;
//        while (!isEnd) {
//            menu();
//            option = sc.nextInt();
//            switch (option) {
//                case 0:
//                    isEnd = true;
//                    break;
//                case 1:
//                    loadEmployee();
//                    break;
//                case 2:
//                    loadHours();
//                    break;
//                case 3:
//                    addEmployeeMenu();
//                    break;
//                case 4:
//                    addHoursMenu();
//                    break;
//                case 5:
//                    calculateWage();
//                    break;
//                default:
//                    System.out.println("Neplatná volba!");
//                    break;
//            }
//        }
//    }
//
//    public static void menu() {
//        System.out.println("****************************");
//        System.out.println("PayrollSystem");
//        System.out.println("Zvolte volbu: ");
//        System.out.println("1 - načtení souboru se zaměstnanci");
//        System.out.println("2 - načtení souboru s odpracovanými hodinami");
//        System.out.println("3 - přidání zaměstnance");
//        System.out.println("4 - přidání odpracovaných hodin zaměstnanci");
//        System.out.println("5 - výpočet mezd");
//        System.out.println("0 - ukončení programu");
//        System.out.println("****************************");
//    }
}
