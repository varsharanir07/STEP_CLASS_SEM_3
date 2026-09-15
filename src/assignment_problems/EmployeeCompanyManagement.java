package assignment_problems;

class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println("Company: " + companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class EmployeeCompanyManagement {
    public static void main(String[] args) {

        new Employee("Asha", 40000);
        new Employee("Rahul", 50000);
        new Employee("Kiran", 45000);

        Employee.printCompanyInfo();
    }
}
