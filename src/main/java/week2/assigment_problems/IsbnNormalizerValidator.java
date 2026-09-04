package week2.assigment_problems;

import java.util.Scanner;

/**
 * Week 2 Assignment - Problem 4 : Library ISBN Normalizer and Validator
 *
 * A valid code is 13 characters: 3 letters (publisher) + 4 digits (year)
 * + 6 digits (catalog number).
 */
public class IsbnNormalizerValidator {

    // Trim, then uppercase ONLY the first three characters - the rest is untouched
    static String normalizeCode(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;
        }

        String publisher = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return publisher + rest;
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: year and catalog number must be digits";
            }
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        return "[" + publisher + "] YEAR: " + year + " | CATALOG: " + catalog;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter raw code: ");
        String raw = sc.nextLine();

        String normalized = normalizeCode(raw);
        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}
