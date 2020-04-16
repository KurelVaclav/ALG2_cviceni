package comparingStudents;

import comparingStudents.mycomparing.CompareInterface;
import java.util.Objects;

/**
 *
 * @author Václav Kurel
 */
public class Student implements CompareInterface, Comparable<Student> {

    private String firstName;
    private String lastName;
    private int studentNumber;
    //TODO pole známek 
    private int[] znamky;

    public Student(String firstName, String lastName, int studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int[] getZnamky() {
        return znamky;
    }

    public void setZnamky(int[] znamky) {
        this.znamky = znamky;
    }

    //TODO calculate average
    public double calculateAverage() {
        double prumer;
        int sum = 0;
        int count = 0;
        for (int i = 0; i < znamky.length; i++) {
            sum += znamky[i];
            count++;
        }
        prumer = (double) sum / count;
        return prumer;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%10d%10.2f", firstName, lastName, studentNumber, calculateAverage());
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + this.studentNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

}
