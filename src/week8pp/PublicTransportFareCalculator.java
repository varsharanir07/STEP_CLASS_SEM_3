package week8pp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 5: Public Transport Fare Calculator
 * 
 * Task:
 * A public transport system calculates fares based on the type of transport (e.g., Bus, Train, Metro)
 * and distance traveled. Each transport type has a different fare structure. The system needs to
 * calculate the fare for a list of journeys and display the individual and total fares.
 * 
 * Input:
 * The first line contains an integer N, the number of journeys. Each of the next N lines contains
 * TransportType Distance [AdditionalParam]:
 * - BUS Distance
 * - TRAIN Distance
 * - METRO Distance PeakHourFactor
 * 
 * Output:
 * For each journey, display TransportType: CalculatedFare, formatted to two decimal places.
 * Finally, display Total: GrandTotalFare, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Bus: Base fare $2, plus $0.10 per km. Max fare $10.
 * - Train: Base fare $3, plus $0.15 per km.
 * - Metro: Base fare $1.50, plus $0.20 per km, multiplied by a PeakHourFactor.
 * 
 * Constraints:
 * - 1 <= N <= 100
 * - Distance is a positive number.
 * - PeakHourFactor is a float between 1.0 and 2.0 (inclusive).
 * 
 * Instructions:
 * Implement a system where different transport types can calculate their fares according to their
 * specific rules, allowing a central system to process all journey requests uniformly.
 * Consider how to model different transport types that share a common operation (calculating a fare)
 * but have distinct pricing logic. Focus on how a central system can calculate fares for a collection
 * of different transport types without explicit type checking.
 */
abstract class TransportJourney {
    protected final String transportType;
    protected final double distance;

    public TransportJourney(String transportType, double distance) {
        this.transportType = transportType;
        this.distance = distance;
    }

    public String getTransportType() {
        return transportType;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method to calculate fare polymorphically
    public abstract double calculateFare();
}

class BusJourney extends TransportJourney {
    public BusJourney(double distance) {
        super("BUS", distance);
    }

    @Override
    public double calculateFare() {
        // Base fare $2, plus $0.10 per km. Max fare $10.
        return Math.min(10.00, 2.00 + (0.10 * distance));
    }
}

class TrainJourney extends TransportJourney {
    public TrainJourney(double distance) {
        super("TRAIN", distance);
    }

    @Override
    public double calculateFare() {
        // Base fare $3, plus $0.15 per km.
        return 3.00 + (0.15 * distance);
    }
}

class MetroJourney extends TransportJourney {
    private final double peakHourFactor;

    public MetroJourney(double distance, double peakHourFactor) {
        super("METRO", distance);
        this.peakHourFactor = peakHourFactor;
    }

    public double getPeakHourFactor() {
        return peakHourFactor;
    }

    @Override
    public double calculateFare() {
        // Base fare $1.50, plus $0.20 per km, multiplied by a PeakHourFactor.
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
}

class PublicTransportSystem {
    private final List<TransportJourney> journeys = new ArrayList<>();

    public void addJourney(TransportJourney journey) {
        if (journey != null) {
            journeys.add(journey);
        }
    }

    // Process all journeys uniformly through dynamic method dispatch
    public void processAllFares() {
        double grandTotalFare = 0.0;
        for (TransportJourney j : journeys) {
            double fare = j.calculateFare();
            grandTotalFare += fare;
            System.out.printf("%s: %.2f%n", j.getTransportType(), fare);
        }
        System.out.printf("Total: %.2f%n", grandTotalFare);
    }
}

public class PublicTransportFareCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            PublicTransportSystem system = new PublicTransportSystem();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double distance = sc.nextDouble();
                if ("METRO".equalsIgnoreCase(type)) {
                    double peakHourFactor = sc.nextDouble();
                    system.addJourney(new MetroJourney(distance, peakHourFactor));
                } else if ("TRAIN".equalsIgnoreCase(type)) {
                    system.addJourney(new TrainJourney(distance));
                } else if ("BUS".equalsIgnoreCase(type)) {
                    system.addJourney(new BusJourney(distance));
                }
            }
            system.processAllFares();
        } else {
            // Default sample demo if no input piped
            PublicTransportSystem system = new PublicTransportSystem();
            system.addJourney(new BusJourney(15));
            system.addJourney(new TrainJourney(50));
            system.addJourney(new MetroJourney(10, 1.5));
            system.processAllFares();
        }
        sc.close();
    }
}
