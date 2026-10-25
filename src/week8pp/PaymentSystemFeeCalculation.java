package week8pp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 1: Payment System Fee Calculation
 * 
 * Task:
 * A payment processing system handles various transaction types. Each payment method applies
 * a different processing fee. For every transaction, calculate the final amount after applying
 * the applicable fee and display the adjusted amount for each transaction followed by the
 * total amount processed.
 * 
 * Input:
 * The first line contains an integer N, the number of transactions. Each of the next N lines
 * contains a payment type and transaction amount:
 * - CARD Amount
 * - WALLET Amount
 * - BANKTRANSFER Amount
 * 
 * Output:
 * For each transaction, display PaymentType: AdjustedAmount, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Card transactions have a 2% processing fee.
 * - Wallet transactions have a 1% processing fee.
 * - Bank Transfers have no processing fee.
 * 
 * Constraints:
 * - 1 <= N <= 1000
 * - 1 <= Amount <= 1000000
 * 
 * Instructions:
 * Implement the solution so that each payment type can apply its own calculation behavior
 * while the main processing flow handles all transactions uniformly.
 * Consider how to model different payment methods that share a common operation (calculating the final amount)
 * but have distinct fee calculation logic. Focus on how a central system can process a collection of
 * different payment types without explicit type checking.
 */
abstract class PaymentMethod {
    protected final String paymentType;
    protected final double amount;

    public PaymentMethod(String paymentType, double amount) {
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public double getAmount() {
        return amount;
    }

    // Abstract method to be overridden polymorphically by each payment method
    public abstract double calculateFinalAmount();
}

class CardPayment extends PaymentMethod {
    public CardPayment(double amount) {
        super("CARD", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // 2% processing fee
        return amount * 1.02;
    }
}

class WalletPayment extends PaymentMethod {
    public WalletPayment(double amount) {
        super("WALLET", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // 1% processing fee
        return amount * 1.01;
    }
}

class BankTransferPayment extends PaymentMethod {
    public BankTransferPayment(double amount) {
        super("BANKTRANSFER", amount);
    }

    @Override
    public double calculateFinalAmount() {
        // No processing fee
        return amount;
    }
}

class PaymentProcessingSystem {
    private final List<PaymentMethod> transactions = new ArrayList<>();

    public void addTransaction(PaymentMethod payment) {
        if (payment != null) {
            transactions.add(payment);
        }
    }

    // Process transactions uniformly through dynamic method dispatch
    public void processAllTransactions() {
        double grandTotal = 0.0;
        for (PaymentMethod tx : transactions) {
            double finalAmount = tx.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f%n", tx.getPaymentType(), finalAmount);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}

public class PaymentSystemFeeCalculation {
    public static PaymentMethod createPayment(String type, double amount) {
        if ("CARD".equalsIgnoreCase(type)) {
            return new CardPayment(amount);
        } else if ("WALLET".equalsIgnoreCase(type)) {
            return new WalletPayment(amount);
        } else if ("BANKTRANSFER".equalsIgnoreCase(type)) {
            return new BankTransferPayment(amount);
        }
        throw new IllegalArgumentException("Unknown payment type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            PaymentProcessingSystem system = new PaymentProcessingSystem();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();
                system.addTransaction(createPayment(type, amount));
            }
            system.processAllTransactions();
        } else {
            // Default sample demo if no input piped
            PaymentProcessingSystem system = new PaymentProcessingSystem();
            system.addTransaction(createPayment("CARD", 1000));
            system.addTransaction(createPayment("WALLET", 500));
            system.addTransaction(createPayment("BANKTRANSFER", 2000));
            system.processAllTransactions();
        }
        sc.close();
    }
}
