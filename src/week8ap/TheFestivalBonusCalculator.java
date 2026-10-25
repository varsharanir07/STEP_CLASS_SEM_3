package week8ap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 4: The Festival Bonus Calculator
 * 
 * Task:
 * A company pays a festival bonus to its employees. Full-time employees, part-time employees,
 * and interns each receive the bonus in a different way. For every employee, calculate the bonus,
 * display it with the employee's name, and then display the total bonus paid.
 * 
 * Input:
 * The first line contains an integer N, the number of employees. Each of the next N lines contains
 * EmployeeType Name MonthlySalary:
 * - FULLTIME Name MonthlySalary
 * - PARTTIME Name MonthlySalary
 * - INTERN Name MonthlySalary
 * 
 * Output:
 * For each employee, display Name: Bonus, formatted to two decimal places.
 * Finally, display Total Bonus: GrandTotal, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Full-time employees get 10% of their monthly salary.
 * - Part-time employees get 5% of their monthly salary.
 * - Interns get a fixed bonus of ₹2,000, whatever their salary.
 * 
 * Constraints:
 * - 1 <= N <= 1000
 * - Name is a single word
 * - 1000 <= MonthlySalary <= 500000
 * 
 * Instructions:
 * Consider how to model different employee types that share common details (name and salary) and a
 * common operation (calculating the bonus), while each type follows its own bonus rule. Focus on how the
 * payroll system can process a list of employees without explicit type checking.
 */
abstract class Employee {
    protected final String employeeType;
    protected final String name;
    protected final double monthlySalary;

    public Employee(String employeeType, String name, double monthlySalary) {
        this.employeeType = employeeType;
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getName() {
        return name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    // Abstract method defining the common bonus calculation logic
    public abstract double calculateBonus();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super("FULLTIME", name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // 10% of monthly salary
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super("PARTTIME", name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // 5% of monthly salary
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double monthlySalary) {
        super("INTERN", name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // Fixed bonus of ₹2,000
        return 2000.00;
    }
}

class FestivalPayrollSystem {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
        }
    }

    // Process all employees uniformly through dynamic dispatch
    public void processAllBonuses() {
        double grandTotal = 0.0;
        for (Employee emp : employees) {
            double bonus = emp.calculateBonus();
            grandTotal += bonus;
            System.out.printf("%s: %.2f%n", emp.getName(), bonus);
        }
        System.out.printf("Total Bonus: %.2f%n", grandTotal);
    }
}

public class TheFestivalBonusCalculator {
    public static Employee createEmployee(String type, String name, double salary) {
        if ("FULLTIME".equalsIgnoreCase(type)) {
            return new FullTimeEmployee(name, salary);
        } else if ("PARTTIME".equalsIgnoreCase(type)) {
            return new PartTimeEmployee(name, salary);
        } else if ("INTERN".equalsIgnoreCase(type)) {
            return new InternEmployee(name, salary);
        }
        throw new IllegalArgumentException("Unknown employee type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            FestivalPayrollSystem payroll = new FestivalPayrollSystem();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                String name = sc.next();
                double salary = sc.nextDouble();
                payroll.addEmployee(createEmployee(type, name, salary));
            }
            payroll.processAllBonuses();
        } else {
            // Default sample demo if no input piped
            FestivalPayrollSystem payroll = new FestivalPayrollSystem();
            payroll.addEmployee(createEmployee("FULLTIME", "Asha", 50000));
            payroll.addEmployee(createEmployee("PARTTIME", "Ravi", 30000));
            payroll.addEmployee(createEmployee("INTERN", "Neha", 15000));
            payroll.processAllBonuses();
        }
        sc.close();
    }
}
