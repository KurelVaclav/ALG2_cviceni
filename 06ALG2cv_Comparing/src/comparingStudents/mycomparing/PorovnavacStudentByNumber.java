package comparingStudents.mycomparing;

import comparingStudents.Student;

/**
 *
 * @author Václav Kurel
 */
public class PorovnavacStudentByNumber implements ComparatorInterface {

    //přetypování
    @Override
    public boolean bigger(Object o1, Object o2) {
        return ((Student) o1).getStudentNumber() > ((Student) o2).getStudentNumber();
    }

}
