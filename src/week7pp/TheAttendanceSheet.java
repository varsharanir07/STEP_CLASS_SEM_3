package week7pp;

/**
 * Problem 5: The Attendance Sheet
 * 
 * Scenario:
 * A teacher marks which students are present in class today.
 * 
 * Problem Statement:
 * Design an AttendanceSheet class that stores present students internally but only reveals a count and a yes/no lookup — never the full list.
 * 
 * Requirements:
 * - Store the names of present students in a private array (fixed size is fine — assume a maximum class size).
 * - Provide a method to mark a student present.
 * - Provide a method that returns how many students are present, and another that checks whether one specific name is present.
 * - There should be no method that returns the whole array of names.
 */
class AttendanceSheet {
    private final String[] presentStudents;
    private int count;

    public AttendanceSheet(int maxClassSize) {
        this.presentStudents = new String[maxClassSize > 0 ? maxClassSize : 30];
        this.count = 0;
    }

    public boolean isPresent(String studentName) {
        if (studentName == null) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (studentName.equalsIgnoreCase(presentStudents[i])) {
                return true;
            }
        }
        return false;
    }

    public void markPresent(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            return;
        }
        if (!isPresent(studentName) && count < presentStudents.length) {
            presentStudents[count++] = studentName;
        }
    }

    public int getPresentCount() {
        return count;
    }
}

public class TheAttendanceSheet {
    public static void main(String[] args) {
        AttendanceSheet sheet = new AttendanceSheet(30);
        System.out.println("AttendanceSheet sheet = new AttendanceSheet(30);");

        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");

        System.out.println("sheet.getPresentCount() -> " + sheet.getPresentCount());
        System.out.println("sheet.isPresent(\"Ben\") -> " + sheet.isPresent("Ben"));
        System.out.println("sheet.isPresent(\"Chen\") -> " + sheet.isPresent("Chen"));
    }
}
