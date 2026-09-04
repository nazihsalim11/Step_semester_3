package week2.class_problems;

import java.util.Scanner;

/**
 * Week 2 Practice - Problem 1 : Vowel and Consonant Counter
 *
 * Walks the string one character at a time with charAt() and sorts each
 * letter into vowels or consonants. Spaces are ignored entirely.
 */
public class VowelConsonantCounter {

    static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Force one case so 'A' and 'a' are treated the same
            char lower = Character.toLowerCase(ch);

            if (lower < 'a' || lower > 'z') {
                continue;                 // space, digit, punctuation - skip it
            }

            if (lower == 'a' || lower == 'e' || lower == 'i'
                    || lower == 'o' || lower == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        countVowelsAndConsonants(text);
        sc.close();
    }
}
