package week7pp;

/**
 * Problem 1: The Piggy Bank
 * 
 * Scenario:
 * A savings app tracks how much money a kid has put away.
 * 
 * Problem Statement:
 * Create a PiggyBank class where money can only be added or removed through specific actions — never set directly to any amount.
 * 
 * Requirements:
 * - The savings amount must be private, changed only by deposit and withdraw methods.
 * - A withdrawal larger than the current savings must be rejected, not applied.
 * - Give the piggy bank a final ID that's fixed the moment it's created.
 * - Provide a way to check the current savings, but no way to set it directly.
 */
class PiggyBank {
    private final String id;
    private double savings;

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0; // starts at 0 savings
    }

    public String getId() {
        return id;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            savings += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= savings) {
            savings -= amount;
            return true;
        }
        return false; // rejected
    }

    public double getSavings() {
        return savings;
    }
}

public class ThePiggyBank {
    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        System.out.println("PiggyBank pb = new PiggyBank(\"PB-1\");");

        pb.deposit(100);
        System.out.println("pb.deposit(100) -> savings = " + (int) pb.getSavings());

        pb.withdraw(30);
        System.out.println("pb.withdraw(30) -> savings = " + (int) pb.getSavings());

        boolean success = pb.withdraw(500);
        System.out.println("pb.withdraw(500) -> " + (success ? "success" : "rejected, savings stays " + (int) pb.getSavings()));
    }
}
