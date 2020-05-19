package ui;

import java.util.Scanner;
import app.PayrollEditor;
import java.io.FileNotFoundException;
import java.io.IOException;
import utils.PayrollInterface;

/**
 *
 * @author Václav Kurel
 */
public class payrollSystem {

    public static Scanner sc = new Scanner(System.in);
    public static PayrollInterface pay;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        pay = new PayrollEditor();
        System.out.println("Zadejte název souboru se zaměstnanci: ");
        String employeeFile = sc.next();
        pay.loadEmployees(employeeFile);
        System.out.println(pay.getEmployeesInfo());
        System.out.println("Zadejte název souboru hodinových mezd: ");
        String wagesFile = sc.next();
        pay.loadHours(wagesFile);
        System.out.println(pay.getWagesInfo());
        System.out.println("Zadejte název výstupního souboru: ");
        String resultFile = sc.next();
        pay.saveWages(resultFile);
    }

}
