package assignment_problems;

class Employee {
    String empId, empName;
    double salary;
    boolean isIntern;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        isIntern = false;
    }

    Employee(String empId, String empName) {
        this(empId, empName, 0);
        isIntern = true;
    }

    void printProfile() {
        System.out.println(empId + " | " + empName +
                " | Rs " + salary + " | Intern: " + isIntern);
    }
}

public class EmployeeProfileCreation {
    public static void main(String[] args) {

        Employee e1 = new Employee("E-101", "Divya", 65000);
        Employee e2 = new Employee("E-102", "Arjun");

        e1.printProfile();
        e2.printProfile();
    }
}
