package week2.assigment_problems;

import java.util.Scanner;

/**
 * Week 2 Assignment - Problem 2 : Word Reversal Encoder
 *
 * Each word is reversed on its own, but the ORDER of the words stays the same:
 * "hello club" becomes "olleh bulc", not "bulc olleh".
 */
public class WordReversalEncoder {

    // Reverses one single word by reading it backwards
    private static String reverseWord(String word) {
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }
        return reversed;
    }

    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            result = result + reverseWord(words[i]);

            // Add a space after every word except the last one
            if (i < words.length - 1) {
                result = result + " ";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        System.out.println(reverseEachWord(sentence));
        sc.close();
    }
}
