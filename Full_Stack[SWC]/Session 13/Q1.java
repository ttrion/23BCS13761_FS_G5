import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

public class Q1 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "John", 65000));
        employees.add(new Employee(102, "Alice", 45000));
        employees.add(new Employee(103, "Bob", 75000));
        employees.add(new Employee(104, "David", 55000));

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());

        System.out.printf("%-10s %-20s %-15s%n", "ID", "NAME", "SALARY");

        filteredEmployees.forEach(e ->
                System.out.printf(
                        "%-10d %-20s %-15.2f%n",
                        e.getId(),
                        e.getName(),
                        e.getSalary()
                )
        );
    }
}