package competition.app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Václav Kurel
 */
public class Runner implements Comparable<Runner> {

    int number;
    String firstName;
    String lastName;
    private LocalTime startTime;
    private LocalTime finishTime;
    public static DateTimeFormatter dtfStart = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter dtfFinish = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    public Runner(int number, String firstName, String lastName) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime, dtfStart);
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = LocalTime.parse(finishTime, dtfFinish);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public String getStartTimeString() {
        return startTime.format(dtfStart);
    }

    public String getFinishTimeString() {
        return finishTime.format(dtfFinish);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Runner o) {
        return this.runningTime().compareTo(o.runningTime());
    }

    public LocalTime runningTime() {
        return LocalTime.ofNanoOfDay(finishTime.toNanoOfDay() - startTime.toNanoOfDay()); //nanosekundy
    }

    @Override
    public String toString() {
        return String.format("%-4d%-10s%-10s%-15s%-15s%-15s", number, firstName, lastName,
                getStartTimeString(), getFinishTimeString(), runningTime().format(dtfFinish));
    }

//    //test main
//    public static void main(String[] args) {
//        Runner r = new Runner(101, "Alice", "Malá");
//        r.setStartTime("09:00:00");
//        r.setFinishTime("09:31:20:000");
//        System.out.println(r);
//    }
}
