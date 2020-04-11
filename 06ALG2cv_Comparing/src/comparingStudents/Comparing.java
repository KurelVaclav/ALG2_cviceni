package comparingStudents;

import static comparingStudents.MyComparing.print;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Václav Kurel
 */
public class Comparing {

    public static void main(String[] args) {
        Student[] students = DataSource.loadDataAsArray();
        print(students);
        System.out.println("Sort by number: ");
        Arrays.sort(students); //students musí být typově kompatibilní s interface Comparable
        print(students);
        System.out.println("S listem: ");
        List<Student> students1 = DataSource.loadDataAsList();
        print(students1); //přetížit metodu print() pro list v MyComparing
        System.out.println("Sort by number: ");
        Collections.sort(students1); //volat jinou třídu!
        print(students1);
    }
}
