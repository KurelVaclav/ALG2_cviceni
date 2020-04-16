package comparingStudents.mycomparing;

import comparingStudents.Student;

/**
 *
 * @author Václav Kurel
 */
public class PorovnavacStudentByFirstName implements ComparatorInterface {

    //String je typově kompatibilní s Comparable
    @Override
    public boolean bigger(Object o1, Object o2) {
        return ((Student) o1).getFirstName().compareTo(((Student) o2).getFirstName()) > 0; //compareTo vrací int 1/0/-1 někdy i vetší jak 1
    }
}
