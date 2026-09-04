package week1.class_problems;

import java.util.Scanner;

/**
 * Problem 2 - Palindrome Checker (3 Approaches)
 *
 * The same question answered three independent ways: a loop from both ends,
 * recursion, and a reversed character array. All three must always agree.
 */
public class PalindromeChecker {

    // Approach 1: walk inward from both ends
    static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: recursion - compare the outer pair, then shrink the window
    static boolean isPalindromeRecursive(String text) {
        return checkRecursive(text, 0, text.length() - 1);
    }

    private static boolean checkRecursive(String text, int left, int right) {
        if (left >= right) {          // base case: 0 or 1 character left in the middle
            return true;
        }
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        return checkRecursive(text, left + 1, right - 1);
    }

    // Approach 3: build the reverse into a char array, then compare position by position
    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    private static String label(boolean result) {
        return result ? "Palindrome" : "Not Palindrome";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.println("Iterative: " + label(isPalindromeIterative(text))
                + " | Recursive: " + label(isPalindromeRecursive(text))
                + " | Array Reversal: " + label(isPalindromeArrayReversal(text)));

        sc.close();
    }
}
