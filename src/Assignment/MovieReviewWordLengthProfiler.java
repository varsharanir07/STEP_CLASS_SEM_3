package Assignment;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {

        String[] words = review.split(" ");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {

            int length = word.length();

            if (length >= 1 && length <= 4)
                shortCount++;
            else if (length <= 8)
                mediumCount++;
            else
                longCount++;
        }

        System.out.println("Short Words : " + shortCount);
        System.out.println("Medium Words: " + mediumCount);
        System.out.println("Long Words  : " + longCount);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Movie Review: ");
        String review = sc.nextLine();

        classifyWordLengths(review);

        sc.close();
    }
}
