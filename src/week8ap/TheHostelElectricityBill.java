package week8ap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 3: The Hostel Electricity Bill
 * 
 * Task:
 * A hostel has three kinds of rooms: single rooms, shared rooms, and AC rooms. Each room type
 * calculates its monthly electricity bill differently. For shared rooms, the bill is split equally
 * among the occupants, and the amount shown is what each occupant pays. For every room, calculate
 * the bill, display it, and then display the total of all bills shown.
 * 
 * Input:
 * The first line contains an integer N, the number of rooms. Each of the next N lines contains
 * RoomType Units [AdditionalParam]:
 * - SINGLE Units
 * - SHARED Units Occupants
 * - AC Units
 * 
 * Output:
 * For each room, display RoomType: BillAmount, formatted to two decimal places.
 * Finally, display Total: GrandTotal, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Single room: ₹8 per unit.
 * - Shared room: ₹6 per unit, divided equally by the number of occupants.
 * - AC room: ₹10 per unit, plus a fixed charge of ₹200.
 * 
 * Constraints:
 * - 1 <= N <= 200
 * - Units is a positive whole number
 * - 2 <= Occupants <= 4
 * 
 * Instructions:
 * Consider how to model different room types that share a common operation (calculating the bill) but use
 * different rules. Notice that a shared room needs one extra value (occupants); think about where that value
 * should be stored so the main processing flow can still treat every room the same way.
 */
abstract class Room {
    protected final String roomType;
    protected final int units;

    public Room(String roomType, int units) {
        this.roomType = roomType;
        this.units = units;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getUnits() {
        return units;
    }

    // Common abstract method for uniform bill calculation
    public abstract double calculateBill();
}

class SingleRoom extends Room {
    public SingleRoom(int units) {
        super("SINGLE", units);
    }

    @Override
    public double calculateBill() {
        // ₹8 per unit
        return units * 8.00;
    }
}

class SharedRoom extends Room {
    // Encapsulate the additional parameter (occupants) inside SharedRoom
    private final int occupants;

    public SharedRoom(int units, int occupants) {
        super("SHARED", units);
        this.occupants = occupants > 0 ? occupants : 1;
    }

    public int getOccupants() {
        return occupants;
    }

    @Override
    public double calculateBill() {
        // ₹6 per unit, divided equally by the number of occupants
        return (units * 6.00) / occupants;
    }
}

class ACRoom extends Room {
    public ACRoom(int units) {
        super("AC", units);
    }

    @Override
    public double calculateBill() {
        // ₹10 per unit, plus a fixed charge of ₹200
        return (units * 10.00) + 200.00;
    }
}

class HostelBillingManager {
    private final List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
        }
    }

    // Process all rooms uniformly via polymorphic dispatch
    public void processAllBills() {
        double grandTotal = 0.0;
        for (Room r : rooms) {
            double bill = r.calculateBill();
            grandTotal += bill;
            System.out.printf("%s: %.2f%n", r.getRoomType(), bill);
        }
        System.out.printf("Total: %.2f%n", grandTotal);
    }
}

public class TheHostelElectricityBill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            HostelBillingManager manager = new HostelBillingManager();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                int units = sc.nextInt();
                if ("SHARED".equalsIgnoreCase(type)) {
                    int occupants = sc.nextInt();
                    manager.addRoom(new SharedRoom(units, occupants));
                } else if ("SINGLE".equalsIgnoreCase(type)) {
                    manager.addRoom(new SingleRoom(units));
                } else if ("AC".equalsIgnoreCase(type)) {
                    manager.addRoom(new ACRoom(units));
                }
            }
            manager.processAllBills();
        } else {
            // Default sample demo if no input piped
            HostelBillingManager manager = new HostelBillingManager();
            manager.addRoom(new SingleRoom(120));
            manager.addRoom(new SharedRoom(150, 3));
            manager.addRoom(new ACRoom(100));
            manager.processAllBills();
        }
        sc.close();
    }
}
