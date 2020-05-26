/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.PayrollEditor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Václav Kurel
 */
public class Tester {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        PayrollEditor pe = new PayrollEditor();
        System.out.println("Zadjete");
        String employeeFile = sc.next();
        pe.loadEmployees(employeeFile);
        System.out.println(pe.getEmployeesInfo());
        System.out.println("Zadjtre");
        String resultFile = sc.next();
        pe.saveResultToExcel(resultFile);
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Java Books");
//
//        Object[][] bookData = {
//            {"Head First Java", "Kathy Serria", 79},
//            {"Effective Java", "Joshua Bloch", 36},
//            {"Clean Code", "Robert martin", 42},
//            {"Thinking in Java", "Bruce Eckel", 35},};
//
//        int rowCount = 0;
//
//        for (Object[] aBook : bookData) {
//            Row row = sheet.createRow(++rowCount);
//
//            int columnCount = 0;
//
//            for (Object field : aBook) {
//                Cell cell = row.createCell(++columnCount);
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
//            }
//
//        }
//
//        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
//            workbook.write(outputStream);
//        }
    }

}
