package week8ap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 1: The Canteen Billing Counter
 * 
 * Task:
 * A college canteen serves three kinds of customers: students, staff, and guests.
 * Each kind of customer pays a different final amount for the same bill. For every bill,
 * calculate the final amount to be paid, display it, and then display the total amount collected.
 * 
 * Input:
 * The first line contains an integer N, the number of bills. Each of the next N lines contains
 * a customer type and the bill amount:
 * - STUDENT Amount
 * - STAFF Amount
 * - GUEST Amount
 * 
 * Output:
 * For each bill, display CustomerType: FinalAmount, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Students get a 10% discount.
 * - Staff get a 5% discount.
 * - Guests pay the full amount plus a ₹10 service charge.
 * 
 * Constraints:
 * - 1 <= N <= 1000
 * - 1 <= Amount <= 100000
 * 
 * Instructions:
 * Consider how to model different customer types that share a common operation (calculating the final amount)
 * but follow different pricing rules. Focus on how the billing counter can process a list of bills from
 * different customer types without checking the type of each customer with if-else blocks.
 */
abstract class Customer {
    protected final String customerType;
    protected final double amount;

    public Customer(String customerType, double amount) {
        this.customerType = customerType;
        this.amount = amount;
    }

    public String getCustomerType() {
        return customerType;
    }

    public double getAmount() {
        return amount;
    }

    // Abstract method defining the common operation overridden polymorphically
    public abstract double calculateFinalAmount();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double amount) {
        super("STUDENT", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // Students receive a 10% discount
        return amount * 0.90;
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double amount) {
        super("STAFF", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // Staff receive a 5% discount
        return amount * 0.95;
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double amount) {
        super("GUEST", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // Guests pay full amount plus ₹10 service charge
        return amount + 10.00;
    }
}

class CanteenBillingCounter {
    private final List<Customer> bills = new ArrayList<>();

    public void addBill(Customer customer) {
        if (customer != null) {
            bills.add(customer);
        }
    }

    // Process all bills uniformly through polymorphic dispatch without type-checking
    public void processAllBills() {
        double grandTotal = 0.0;
        for (Customer bill : bills) {
            double finalAmount = bill.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f%n", bill.getCustomerType(), finalAmount);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}

public class TheCanteenBillingCounter {
    public static Customer createCustomer(String type, double amount) {
        if ("STUDENT".equalsIgnoreCase(type)) {
            return new StudentCustomer(amount);
        } else if ("STAFF".equalsIgnoreCase(type)) {
            return new StaffCustomer(amount);
        } else if ("GUEST".equalsIgnoreCase(type)) {
            return new GuestCustomer(amount);
        }
        throw new IllegalArgumentException("Unknown customer type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            CanteenBillingCounter counter = new CanteenBillingCounter();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();
                counter.addBill(createCustomer(type, amount));
            }
            counter.processAllBills();
        } else {
            // Default sample demo if no input piped
            CanteenBillingCounter counter = new CanteenBillingCounter();
            counter.addBill(createCustomer("STUDENT", 200));
            counter.addBill(createCustomer("STAFF", 300));
            counter.addBill(createCustomer("GUEST", 150));
            counter.processAllBills();
        }
        sc.close();
    }
}
