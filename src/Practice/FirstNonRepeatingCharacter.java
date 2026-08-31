package Practice;

import java.util.HashMap;
import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    static char findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : text.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        for (char ch : text.toCharArray()) {
            if (map.get(ch) == 1)
                return ch;
        }

        return '\0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: " + result);

        sc.close();
    }
}
