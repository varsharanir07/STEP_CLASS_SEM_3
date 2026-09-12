package practice_problems;

public class HackathonSeatingGridOptimizer {

    static double rowAverage(int[] row) {

        int sum = 0;

        for (int value : row) {
            sum += value;
        }

        return (double) sum / row.length;
    }

    static String classifyRows(int[][] seatingScores, int threshold) {

        String result = "";

        for (int i = 0; i < seatingScores.length; i++) {

            double avg = rowAverage(seatingScores[i]);

            if (i > 0) {
                result += " | ";
            }

            if (avg >= threshold) {
                result += "Row " + i + ": Buzzing Zone";
            } else {
                result += "Row " + i + ": Quiet Zone";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] scores = {
                {40, 50, 45},
                {85, 90, 95},
                {30, 20, 25}
        };

        System.out.println(classifyRows(scores, 60));
    }
}
