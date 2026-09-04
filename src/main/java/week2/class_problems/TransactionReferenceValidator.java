package week2.class_problems;

import java.util.Scanner;

/**
 * Week 2 Practice - Problem 5 : Bank Transaction Reference Generator and Validator
 *
 * A valid reference is 14 characters: 3 letters (bank code) + 6 digits (ddMMyy)
 * + 5 digits (sequence). Normalisation happens first, validation second.
 */
public class TransactionReferenceValidator {

    // Trim the stray spaces, then uppercase ONLY the first three characters
    static String normalizeReference(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;              // too short to have a bank code at all
        }

        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return bankCode + rest;
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        // First three characters must be letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Remaining eleven must be digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: date and sequence must be digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);

        return "[" + bankCode + "] DATE: " + day + "/" + month + "/" + year
                + " | SEQ: " + sequence;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter raw reference: ");
        String raw = sc.nextLine();

        String normalized = normalizeReference(raw);
        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}
