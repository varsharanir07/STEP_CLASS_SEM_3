package week7pp;

/**
 * Problem 4: The Locker Code
 * 
 * Scenario:
 * A gym locker has a combination code that members can change.
 * 
 * Problem Statement:
 * Design a Locker class where the combination can be changed, but never read back directly from outside the class.
 * 
 * Requirements:
 * - The combination code must be private, with no getter at all.
 * - Provide a method to change the code that requires the current code to be entered correctly first.
 * - If the wrong current code is given, the change must be rejected and the code must stay the same.
 * - Give the locker a final locker number, fixed at creation.
 */
class Locker {
    private final int lockerNumber;
    private String combinationCode;

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.combinationCode = initialCode;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public boolean changeCode(String currentCode, String newCode) {
        if (this.combinationCode != null && this.combinationCode.equals(currentCode)) {
            this.combinationCode = newCode;
            return true;
        }
        return false;
    }
}

public class TheLockerCode {
    public static void main(String[] args) {
        Locker l = new Locker(101, "1234");
        System.out.println("Locker l = new Locker(101, \"1234\");");

        boolean res1 = l.changeCode("1234", "5678");
        System.out.println("l.changeCode(\"1234\", \"5678\") -> " + (res1 ? "success" : "rejected"));

        boolean res2 = l.changeCode("0000", "9999");
        System.out.println("l.changeCode(\"0000\", \"9999\") -> " + (res2 ? "success" : "rejected, code is still \"5678\""));
    }
}
