package app;

import java.time.LocalDate;

/**
 *
 * @author Václav Kurel
 */
public class Wage{

   // private LocalDate day;
    private Employee employee;
    private int hours;

    public Wage(Employee employee, int hours) {
        this.employee = employee;
        this.hours = hours;
    }
    
    
    
}
