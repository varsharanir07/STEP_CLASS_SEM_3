package week7pp;

/**
 * Problem 2: The Quiz Scorecard
 * 
 * Scenario:
 * A quiz app records whether each answer you gave was right or wrong.
 * 
 * Problem Statement:
 * Design a Scorecard class that stores each answer's result privately, and reveals only the final score — never the raw list of right/wrong answers.
 * 
 * Requirements:
 * - Store the results (true for correct, false for incorrect) in a private array, filled in one answer at a time.
 * - Provide a method to record the next answer's result.
 * - Expose only the total score (count of correct answers) — never the array itself, in any form.
 * - The total number of questions must be fixed when the scorecard is created.
 */
class Scorecard {
    private final int totalQuestions;
    private final boolean[] results;
    private int recordedCount;

    public Scorecard(int totalQuestions) {
        this.totalQuestions = totalQuestions > 0 ? totalQuestions : 0;
        this.results = new boolean[this.totalQuestions];
        this.recordedCount = 0;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public boolean recordAnswer(boolean isCorrect) {
        if (recordedCount < totalQuestions) {
            results[recordedCount++] = isCorrect;
            return true;
        }
        return false; // Ignored or rejected if exceeding question count
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < recordedCount; i++) {
            if (results[i]) {
                score++;
            }
        }
        return score;
    }
}

public class TheQuizScorecard {
    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);

        System.out.println("sc.getScore() -> " + sc.getScore());
    }
}
