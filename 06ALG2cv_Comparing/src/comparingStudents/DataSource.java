package comparingStudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * třída s statickými metody pro pole a dynamické pole, abych mohl třídit
 *
 * @author Václav Kurel
 */
public class DataSource {
    
    private static Student[] data = {
        new Student("alice", "Malá", 345),
        new Student("Bob", "Velký", 123),
        new Student("Cyril", "Stredny", 567)
    };

    //metoda na pole - vracet kopii!!
    public static Student[] loadDataAsArray() {
        return Arrays.copyOf(data, data.length); //defenzivní kopie
    }

    //dynamické pole
    public static List<Student> loadDataAsList() {
        return Arrays.asList(data);
//        ArrayList<Student> students = new ArrayList<>(); //prázdný
//        students.addAll(Arrays.asList(data));
    }
    
    
}
