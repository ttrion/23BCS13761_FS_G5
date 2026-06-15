import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }
}

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            double marks = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(id, name, marks));
        }

        students.sort(
                Comparator.comparing(Student::getMarks).reversed()
                        .thenComparing(Student::getName)
        );

        System.out.printf("%-10s %-20s %-10s%n", "ID", "NAME", "MARKS");

        for (Student student : students) {
            System.out.printf(
                    "%-10d %-20s %-10.2f%n",
                    student.getId(),
                    student.getName(),
                    student.getMarks()
            );
        }

        sc.close();
    }
}