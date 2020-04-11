package comparingStudents;

import java.util.List;

/**
 *
 * @author Václav Kurel
 */
public class MyComparing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student[] students = DataSource.loadDataAsArray();
        print(students);
//        System.out.println("Sort by number: ");
//        sortByNumber(students);
//        print(students);
        System.out.println("Sort by number: ");
        sort(students);
        print(students);
    }

    //bubblesort - díky tomu CompareInterface jakýkoliv porovnání dle implementace isBigger(), musí mít isBigger
    public static void sort(CompareInterface[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1].isBigger(array[j])) { //rozmyslet,podle čeho třídit a pak přes metodu
                    CompareInterface temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void sortByNumber(Student[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1].isBigger(array[j])) { //rozmyslet,podle čeho třídit a pak přes metodu
                    Student temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    //metoda pro výpis pole pro všechny co dědí od Object
    public static void print(Object[] array) {
        for (Object object : array) {
            System.out.println(object);
        }
    }

    public static void print(List list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
