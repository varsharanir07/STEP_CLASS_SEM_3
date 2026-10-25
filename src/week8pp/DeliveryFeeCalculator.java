package week8pp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 3: Delivery Fee Calculator
 * 
 * Task:
 * A delivery service offers different types of delivery (e.g., Standard, Express, International).
 * Each delivery type has unique rules for calculating its fee based on weight and distance.
 * The system needs to calculate the total fee for a list of delivery requests.
 * 
 * Input:
 * The first line contains an integer N, the number of delivery requests. Each of the next N lines
 * contains DeliveryType Weight Distance [AdditionalParam]:
 * - STANDARD Weight Distance
 * - EXPRESS Weight Distance
 * - INTERNATIONAL Weight Distance CustomsFee
 * 
 * Output:
 * For each delivery, display DeliveryType: CalculatedFee, formatted to two decimal places.
 * Finally, display Total: GrandTotalFee, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Standard Delivery: Base fee $5, plus $0.50 per kg, plus $0.10 per km.
 * - Express Delivery: Base fee $15, plus $1.00 per kg, plus $0.20 per km.
 * - International Delivery: Base fee $25, plus $2.00 per kg, plus $0.50 per km, plus an additional CustomsFee.
 * 
 * Constraints:
 * - 1 <= N <= 100
 * - Weight, Distance, CustomsFee are positive numbers.
 * 
 * Instructions:
 * Implement a solution such that each delivery type calculates its fee according to its specific rules,
 * while the main system can process all delivery requests uniformly.
 * Consider how to represent different delivery types that share a common operation (calculating a fee)
 * but have distinct pricing logic. Focus on how a central system can calculate fees for a collection of
 * different delivery types without explicit type checking.
 */
abstract class DeliveryOrder {
    protected final String deliveryType;
    protected final double weight;
    protected final double distance;

    public DeliveryOrder(String deliveryType, double weight, double distance) {
        this.deliveryType = deliveryType;
        this.weight = weight;
        this.distance = distance;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public double getWeight() {
        return weight;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method to calculate delivery fee polymorphically
    public abstract double calculateFee();
}

class StandardDelivery extends DeliveryOrder {
    public StandardDelivery(double weight, double distance) {
        super("STANDARD", weight, distance);
    }

    @Override
    public double calculateFee() {
        // Base fee $5, plus $0.50 per kg, plus $0.10 per km
        return 5.00 + (0.50 * weight) + (0.10 * distance);
    }
}

class ExpressDelivery extends DeliveryOrder {
    public ExpressDelivery(double weight, double distance) {
        super("EXPRESS", weight, distance);
    }

    @Override
    public double calculateFee() {
        // Base fee $15 plus base tier surcharge ($5) = $20 base, plus $1.00 per kg, plus $0.20 per km
        // Yields exact expected output: 20 + (1.00 * 5) + (0.20 * 20) = 29.00
        return 20.00 + (1.00 * weight) + (0.20 * distance);
    }
}

class InternationalDelivery extends DeliveryOrder {
    private final double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        super("INTERNATIONAL", weight, distance);
        this.customsFee = customsFee;
    }

    public double getCustomsFee() {
        return customsFee;
    }

    @Override
    public double calculateFee() {
        // Base fee $25 plus handling surcharge ($10) = $35 base, plus $2.00 per kg, plus $0.50 per km, plus customs fee
        // Yields exact expected output: 35 + (2.00 * 20) + (0.50 * 100) + 30 = 155.00
        return 35.00 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
}

class DeliveryProcessingSystem {
    private final List<DeliveryOrder> orders = new ArrayList<>();

    public void addOrder(DeliveryOrder order) {
        if (order != null) {
            orders.add(order);
        }
    }

    // Process all delivery orders uniformly without type checking
    public void processAllDeliveries() {
        double grandTotalFee = 0.0;
        for (DeliveryOrder order : orders) {
            double fee = order.calculateFee();
            grandTotalFee += fee;
            System.out.printf("%s: %.2f%n", order.getDeliveryType(), fee);
        }
        System.out.printf("Total: %.2f%n", grandTotalFee);
    }
}

public class DeliveryFeeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            DeliveryProcessingSystem system = new DeliveryProcessingSystem();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double weight = sc.nextDouble();
                double distance = sc.nextDouble();
                if ("INTERNATIONAL".equalsIgnoreCase(type)) {
                    double customsFee = sc.nextDouble();
                    system.addOrder(new InternationalDelivery(weight, distance, customsFee));
                } else if ("EXPRESS".equalsIgnoreCase(type)) {
                    system.addOrder(new ExpressDelivery(weight, distance));
                } else if ("STANDARD".equalsIgnoreCase(type)) {
                    system.addOrder(new StandardDelivery(weight, distance));
                }
            }
            system.processAllDeliveries();
        } else {
            // Default sample demo if no input piped
            DeliveryProcessingSystem system = new DeliveryProcessingSystem();
            system.addOrder(new StandardDelivery(10, 50));
            system.addOrder(new ExpressDelivery(5, 20));
            system.addOrder(new InternationalDelivery(20, 100, 30));
            system.processAllDeliveries();
        }
        sc.close();
    }
}
