package week8ap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 2: The Campus Parking Charge Calculator
 * 
 * Task:
 * The campus parking lot allows bikes, cars, and trucks. Each vehicle type is charged
 * differently based on the number of hours it was parked. For every parked vehicle,
 * calculate the parking charge, display it, and then display the total charge collected.
 * 
 * Input:
 * The first line contains an integer N, the number of parked vehicles. Each of the next N lines
 * contains a vehicle type and the number of hours parked:
 * - BIKE Hours
 * - CAR Hours
 * - TRUCK Hours
 * 
 * Output:
 * For each vehicle, display VehicleType: Charge, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Bike: ₹10 per hour.
 * - Car: ₹30 for the first hour, plus ₹20 for each additional hour.
 * - Truck: ₹50 per hour, with a minimum charge of ₹100.
 * 
 * Constraints:
 * - 1 <= N <= 500
 * - Hours is a whole number, 1 <= Hours <= 24
 * 
 * Instructions:
 * Consider how to model different vehicle types that share a common operation (calculating the parking charge)
 * but have their own charging rules. Focus on how the parking system can calculate charges for a list
 * of different vehicles without explicit type checking.
 */
abstract class Vehicle {
    protected final String vehicleType;
    protected final int hours;

    public Vehicle(String vehicleType, int hours) {
        this.vehicleType = vehicleType;
        this.hours = hours;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getHours() {
        return hours;
    }

    // Abstract method for polymorphic dynamic dispatch
    public abstract double calculateCharge();
}

class Bike extends Vehicle {
    public Bike(int hours) {
        super("BIKE", hours);
    }

    @Override
    public double calculateCharge() {
        // ₹10 per hour
        return hours * 10.00;
    }
}

class Car extends Vehicle {
    public Car(int hours) {
        super("CAR", hours);
    }

    @Override
    public double calculateCharge() {
        // ₹30 for the first hour, plus ₹20 for each additional hour
        if (hours <= 1) {
            return 30.00;
        }
        return 30.00 + (hours - 1) * 20.00;
    }
}

class Truck extends Vehicle {
    public Truck(int hours) {
        super("TRUCK", hours);
    }

    @Override
    public double calculateCharge() {
        // ₹50 per hour, with a minimum charge of ₹100
        return Math.max(100.00, hours * 50.00);
    }
}

class CampusParkingSystem {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicles.add(vehicle);
        }
    }

    // Calculates all charges uniformly using polymorphism
    public void processParkingCharges() {
        double grandTotal = 0.0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            grandTotal += charge;
            System.out.printf("%s: %.2f%n", v.getVehicleType(), charge);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}

public class TheCampusParkingChargeCalculator {
    public static Vehicle createVehicle(String type, int hours) {
        if ("BIKE".equalsIgnoreCase(type)) {
            return new Bike(hours);
        } else if ("CAR".equalsIgnoreCase(type)) {
            return new Car(hours);
        } else if ("TRUCK".equalsIgnoreCase(type)) {
            return new Truck(hours);
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            CampusParkingSystem parkingSystem = new CampusParkingSystem();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                int hours = sc.nextInt();
                parkingSystem.addVehicle(createVehicle(type, hours));
            }
            parkingSystem.processParkingCharges();
        } else {
            // Default sample demo if no input piped
            CampusParkingSystem parkingSystem = new CampusParkingSystem();
            parkingSystem.addVehicle(createVehicle("BIKE", 3));
            parkingSystem.addVehicle(createVehicle("CAR", 4));
            parkingSystem.addVehicle(createVehicle("TRUCK", 1));
            parkingSystem.addVehicle(createVehicle("CAR", 1));
            parkingSystem.processParkingCharges();
        }
        sc.close();
    }
}
