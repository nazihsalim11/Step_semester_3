package week2.class_problems;

import java.util.Scanner;

/**
 * Week 2 Practice - Problem 4 : Masked Phone Number Formatter
 *
 * Validates a 10-digit number, then shows only the last 4 digits so an agent
 * can confirm identity without the full number being on screen.
 */
public class PhoneNumberMasker {

    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Every character must be a digit
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String masked = "";
        for (int i = 0; i < 6; i++) {          // first six digits become X
            masked = masked + "X";
        }

        masked = masked + "-";

        for (int i = 6; i < phone.length(); i++) {   // last four kept as they are
            masked = masked + phone.charAt(i);
        }

        return masked;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.println(maskPhoneNumber(phone));
        sc.close();
    }
}
