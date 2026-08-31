package Practice_problems;

import java.util.Scanner;

public class CSVStudentRecordParser {

    static void parseStudentRecord(String csvLine) {

        String[] data = csvLine.split(",");

        if (data.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + data[0]);
        System.out.println("Roll No: " + data[1]);
        System.out.println("Dept: " + data[2]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV Record: ");
        String input = sc.nextLine();

        parseStudentRecord(input);

        sc.close();
    }
}
