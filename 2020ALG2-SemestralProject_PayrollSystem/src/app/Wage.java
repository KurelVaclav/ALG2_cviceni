package app;

import java.time.LocalDate;

/**
 * třída reprezentující mzdy
 * data: zaměstnance, hrubá mzda, super hrubí mzda, záloha na daň, odvody na sociální a zdravotní pojištění, čistá mzda
 * @author Václav Kurel
 */
public class Wage {

    //data
    // private LocalDate day;
    private Employee employee;
    private int hours;
    private double grossWage; //hrubá mzda
    private double superGrossWage;
    private double downPayment; //záloha na daň
    private double shInsurancePayment; // odvody na sociální a zdravodní pojištění
    private double netWage; //čistá mzda
    static final double ODV_ZAM = 0.34;
    static final double DAN_VYP = 0.15;
    static final double MAXDAN_SLEVA = 2070;
    static final double SOC = 0.065;
    static final double ZDRAV = 0.045;

    public Wage(Employee employee, int hours) {
        this.employee = employee;
        this.hours = hours;
    }

    public void setGrossWage(int hours, Employee e) {
        this.hours = hours;
        Tax tax = e.getTax();
        double t = tax.getHourTax();
        this.grossWage = hours * t;
    }

    public double getGrossWage() {
        return grossWage;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getShInsurancePayment() {
        return shInsurancePayment;
    }

    public void setSuperGrossWage() {
        double contributions = grossWage * ODV_ZAM;
        this.superGrossWage = grossWage + contributions;
    }
    

    public void setDownPayment() {
        double taxAdvance = DAN_VYP * superGrossWage;
        if (taxAdvance > MAXDAN_SLEVA) {
            this.downPayment = taxAdvance - MAXDAN_SLEVA;
        } else {
            this.downPayment = 0;
        }
    }

    public void setSHInsurancePayment() {
        double sInsurance = SOC * getGrossWage();
        double hInsurance = ZDRAV * getGrossWage();
        this.shInsurancePayment = sInsurance + hInsurance;
    }

    public void setNetWage() {
        this.netWage = getGrossWage() - (getDownPayment() + getShInsurancePayment());
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return employee.toString() + wageToString();
    }

    private String wageToString() {
        return String.format("%-10d%-10.2f%-10.2f%-10.2f%-10.2f%-10.2f", hours, grossWage, superGrossWage, downPayment, shInsurancePayment, netWage);
    }

}
