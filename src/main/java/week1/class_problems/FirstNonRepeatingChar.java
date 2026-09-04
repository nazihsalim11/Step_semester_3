package week1.class_problems;

import java.util.Scanner;

/**
 * Problem 4 - First Non-Repeating Character (Unique Letter Hunt)
 *
 * Two passes: the first counts every character, the second finds the earliest
 * character whose count is exactly 1.
 */
public class FirstNonRepeatingChar {

    static char findFirstNonRepeatingChar(String text) {
        // One counter slot per possible character code
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        // Nothing unique found - '\0' is used as the "not found" marker
        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        }

        sc.close();
    }
}
