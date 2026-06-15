import java.util.Scanner;

class Employee {
    private int employeeId;
    private String employeeName;
    private double basicSalary;

    public Employee(int employeeId, String employeeName, double basicSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double calculateGrossSalary() {
        double hra = basicSalary * 0.20;
        double da = basicSalary * 0.10;
        return basicSalary + hra + da;
    }

    public double calculateTax() {
        return calculateGrossSalary() * 0.10;
    }

    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateTax();
    }

    public void displayPayroll() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Gross Salary: " + calculateGrossSalary());
        System.out.println("Tax: " + calculateTax());
        System.out.println("Net Salary: " + calculateNetSalary());
    }
}

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int employeeId = sc.nextInt();
        sc.nextLine();
        String employeeName = sc.nextLine();
        double basicSalary = sc.nextDouble();

        Employee employee = new Employee(employeeId, employeeName, basicSalary);
        employee.displayPayroll();

        sc.close();
    }
}