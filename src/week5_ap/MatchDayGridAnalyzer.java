package week5_ap;

public class MatchDayGridAnalyzer {

    static double rowAverage(int[] row) {

        int sum = 0;

        for (int runs : row)
            sum += runs;

        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {

        String result = "";

        for (int i = 0; i < runsPerOver.length; i++) {

            if (rowAverage(runsPerOver[i]) >= threshold)
                result += "Match " + i + ": Power Surge";
            else
                result += "Match " + i + ": Normal";

            if (i != runsPerOver.length - 1)
                result += " | ";
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] runs = {
                {4, 6, 8},
                {10, 12, 14},
                {2, 3, 1}
        };

        System.out.println(classifyMatches(runs, 8));
    }
}
