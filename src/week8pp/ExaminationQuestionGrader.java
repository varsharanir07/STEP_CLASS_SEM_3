package week8pp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 4: Examination Question Grader
 * 
 * Task:
 * An examination system needs to evaluate answers for different types of questions (e.g., Multiple Choice,
 * True/False, Essay). Each question type has a specific grading logic. The system must process a list of
 * questions with provided answers and determine the score for each, then calculate the total score.
 * 
 * Input:
 * The first line contains an integer N, the number of questions. Each of the next N lines contains
 * QuestionType QuestionText CorrectAnswer StudentAnswer [Points]:
 * - MCQ "What is 2+2?" "4" "4" 10
 * - TF "Water boils at 100C?" "True" "True" 5
 * - ESSAY "Discuss OOP principles" "Polymorphism, Inheritance, Encapsulation" "I talked about Polymorphism and Inheritance." 20
 * 
 * Output:
 * For each question, display QuestionType: Score, formatted to two decimal places.
 * Finally, display Total Score: OverallScore, also formatted to two decimal places.
 * 
 * Business Rules:
 * - Multiple Choice Question (MCQ): Full points if StudentAnswer exactly matches CorrectAnswer.
 * - True/False Question (TF): Full points if StudentAnswer exactly matches CorrectAnswer.
 * - Essay Question (ESSAY): Partial points. If StudentAnswer contains at least two of the keywords
 *   from CorrectAnswer (comma-separated, case-insensitive), award 75% of Points. If one keyword,
 *   award 50%. Otherwise, 0 points.
 * - For simplicity, assume CorrectAnswer for Essay questions is a comma-separated string of keywords.
 * 
 * Constraints:
 * - 1 <= N <= 100
 * - Points are positive integers.
 * - All string comparisons are case-insensitive for essay keywords.
 * 
 * Instructions:
 * Develop a system where different question types can evaluate answers based on their specific grading
 * criteria, allowing a central grader to process all questions uniformly. Your solution should parse
 * the input, grade each question, and then print the individual and total scores as specified.
 * Consider how to model different examination question types that share a common operation (evaluating an answer)
 * but have distinct grading logic. Focus on how a central grading system can process a collection of various
 * question types without explicit type checking.
 */
abstract class ExamQuestion {
    protected final String questionType;
    protected final String questionText;
    protected final String correctAnswer;
    protected final String studentAnswer;
    protected final int points;

    public ExamQuestion(String questionType, String questionText, String correctAnswer, String studentAnswer, int points) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public int getPoints() {
        return points;
    }

    // Abstract method to evaluate question polymorphically
    public abstract double evaluate();
}

class MCQQuestion extends ExamQuestion {
    public MCQQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super("MCQ", questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public double evaluate() {
        // Full points if StudentAnswer exactly matches CorrectAnswer
        if (studentAnswer != null && studentAnswer.trim().equals(correctAnswer != null ? correctAnswer.trim() : "")) {
            return points;
        }
        return 0.00;
    }
}

class TFQuestion extends ExamQuestion {
    public TFQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super("TF", questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public double evaluate() {
        // Full points if StudentAnswer matches CorrectAnswer
        if (studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer != null ? correctAnswer.trim() : "")) {
            return points;
        }
        return 0.00;
    }
}

class EssayQuestion extends ExamQuestion {
    public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super("ESSAY", questionText, correctAnswer, studentAnswer, points);
    }

    @Override
    public double evaluate() {
        if (correctAnswer == null || studentAnswer == null) {
            return 0.00;
        }

        String lowerStudentAnswer = studentAnswer.toLowerCase();
        String[] keywords = correctAnswer.split(",");
        int matchedKeywords = 0;

        for (String kw : keywords) {
            String trimmed = kw.trim().toLowerCase();
            if (!trimmed.isEmpty() && lowerStudentAnswer.contains(trimmed)) {
                matchedKeywords++;
            }
        }

        if (matchedKeywords >= 2) {
            return points * 0.75;
        } else if (matchedKeywords == 1) {
            return points * 0.50;
        }
        return 0.00;
    }
}

class CentralExamGrader {
    private final List<ExamQuestion> questions = new ArrayList<>();

    public void addQuestion(ExamQuestion question) {
        if (question != null) {
            questions.add(question);
        }
    }

    // Uniformly evaluates all questions without type checking
    public void gradeAllQuestions() {
        double overallScore = 0.0;
        for (ExamQuestion q : questions) {
            double score = q.evaluate();
            overallScore += score;
            System.out.printf("%s: %.2f%n", q.getQuestionType(), score);
        }
        System.out.printf("Total Score: %.2f%n", overallScore);
    }
}

public class ExaminationQuestionGrader {
    public static List<String> parseLineTokens(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (m.find()) {
            if (m.group(1) != null) {
                tokens.add(m.group(1));
            } else {
                tokens.add(m.group(2));
            }
        }
        return tokens;
    }

    public static ExamQuestion createQuestion(List<String> tokens) {
        if (tokens.size() < 5) {
            throw new IllegalArgumentException("Insufficient arguments for question: " + tokens);
        }
        String type = tokens.get(0);
        String text = tokens.get(1);
        String correct = tokens.get(2);
        String student = tokens.get(3);
        int points = Integer.parseInt(tokens.get(4));

        if ("MCQ".equalsIgnoreCase(type)) {
            return new MCQQuestion(text, correct, student, points);
        } else if ("TF".equalsIgnoreCase(type)) {
            return new TFQuestion(text, correct, student, points);
        } else if ("ESSAY".equalsIgnoreCase(type)) {
            return new EssayQuestion(text, correct, student, points);
        }
        throw new IllegalArgumentException("Unknown question type: " + type);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = Integer.parseInt(sc.nextLine().trim());
            CentralExamGrader grader = new CentralExamGrader();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                List<String> tokens = parseLineTokens(line);
                grader.addQuestion(createQuestion(tokens));
            }
            grader.gradeAllQuestions();
        } else {
            // Default sample demo if no input piped
            CentralExamGrader grader = new CentralExamGrader();
            List<String> q1 = parseLineTokens("MCQ \"What is the capital of France?\" \"Paris\" \"Paris\" 10");
            List<String> q2 = parseLineTokens("TF \"The Earth is flat?\" \"False\" \"True\" 5");
            List<String> q3 = parseLineTokens("ESSAY \"Name two primary OOP principles.\" \"Inheritance, Polymorphism, Encapsulation\" \"Polymorphism is one.\" 20");
            List<String> q4 = parseLineTokens("ESSAY \"Describe abstraction and composition.\" \"Abstraction, Composition\" \"I talked about abstraction.\" 15");
            grader.addQuestion(createQuestion(q1));
            grader.addQuestion(createQuestion(q2));
            grader.addQuestion(createQuestion(q3));
            grader.addQuestion(createQuestion(q4));
            grader.gradeAllQuestions();
        }
        sc.close();
    }
}
