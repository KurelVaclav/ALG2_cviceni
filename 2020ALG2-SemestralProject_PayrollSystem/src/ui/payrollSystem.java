package ui;

import java.util.Scanner;
import app.PayrollEditor;
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
     */
    public static void main(String[] args) {
        pay = new PayrollEditor();
        System.out.println("Zadejte název souboru se zaměstnanci: ");
        String employeeFile = sc.next();
        System.out.println("Zadejte název souboru hodinových mezd: ");
        String wagesFile = sc.next();
        pay.load(employeeFile, wagesFile);
        System.out.println(pay.getEmployeesInfo());
    }

}
