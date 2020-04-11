package comparingStudents;

/**
 *
 * @author Václav Kurel
 */
public class Student implements CompareInterface, Comparable<Student> {

    private String firstName;
    private String lastName;
    private int studentNumber;

    public Student(String firstName, String lastName, int studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%10d", firstName, lastName, studentNumber);
    }

    public boolean isBigger(Student student) {
        return this.studentNumber > student.studentNumber; //v tomto případě vrátí true jinak false
    }

    @Override
    public boolean isBigger(CompareInterface o) {
        return this.studentNumber > ((Student) o).studentNumber; //přetypovat interface na studenta!!!
    }

    //compareTo umožnujě genericitu (diamanty) - implements Comparable<objekt>
//    @Override
//    public int compareTo(Object o) {
//        return this.studentNumber - ((Student)o).studentNumber; //u integeru mohu zkratku - jinak if else
//    }
    @Override
    public int compareTo(Student o) {
        return this.studentNumber - o.studentNumber;
    }

}
