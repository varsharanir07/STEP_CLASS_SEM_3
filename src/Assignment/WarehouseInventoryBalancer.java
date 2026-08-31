package Assignment;

import java.util.Scanner;

public class WarehouseInventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        int max = sectionA[0];
        String section = "A";
        int index = 0;

        for (int i = 0; i < sectionA.length; i++) {

            totalA += sectionA[i];
            totalB += sectionB[i];

            if (sectionA[i] > max) {
                max = sectionA[i];
                section = "A";
                index = i;
            }

            if (sectionB[i] > max) {
                max = sectionB[i];
                section = "B";
                index = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB)
            System.out.println("Status: Balanced");
        else
            System.out.println("Status: Not Balanced");

        System.out.println("Highest Quantity: " + max +
                " (Section " + section + ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] A = new int[n];
        int[] B = new int[n];

        System.out.println("Section A");

        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            A[i] = sc.nextInt();
        }

        System.out.println("Section B");

        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            B[i] = sc.nextInt();
        }

        analyzeInventory(A, B);

        sc.close();
    }
}

