package comparingStudents;

import comparingStudents.mycomparing.MyComparing;
import static comparingStudents.mycomparing.MyComparing.print;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Václav Kurel
 */
public class Comparing {

    public static void main(String[] args) {
        Student[] students = DataSource.loadDataAsArray();
        //TODO přidat studentům známky
        int[] znamkyAlice = {5, 5, 5, 2};
        students[0].setZnamky(znamkyAlice);
        int[] znamkyBob = {3, 2, 1, 5};
        students[1].setZnamky(znamkyBob);
        int[] znamkyCyril = {2, 1, 1, 1};
        students[2].setZnamky(znamkyCyril);
        //použije equals: test shodnosti objektů
        //nemusí být shodný objekt, stačí když má stejná data
        System.out.println(students[0].equals(students[1])); //předpoklad false
        //
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

        //třídění další podle FirstName
        System.out.println("");
        System.out.println("Sort by FirstName");
        Arrays.sort(students, new ComparatorByFirstName()); //student nemusí být typově kompatibilní s Comparable
        MyComparing.print(students);

        //třídění dle lastName - anonymní třída
        System.out.println("");
        System.out.println("Sort by LastName");
        Arrays.sort(students, new Comparator<Student>() { //anonymní třída implementující ComparatorInterface
            @Override
            //abych použil češtinu
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ")); //tovární metoda
                return col.compare(o1.getLastName(), o2.getLastName());
                // return o1.getLastName().compareTo(o2.getLastName()); //třídění String dle ASCII
            }
        });
        print(students);

//        Arrays.sort(students, (Student o1, Student o2) -> o1.getLastName().compareTo(o2.getLastName())); //lambda zápis
        //TODO setřídit dle průměru
        System.out.println("");
        System.out.println("sort by Average");
        Arrays.sort(students, new ComparatorByAverage());
        print(students);
    }

}
