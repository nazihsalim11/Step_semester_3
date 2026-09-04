package week2.assigment_problems;

import java.util.Scanner;

/**
 * Week 2 Assignment - Problem 1 : ATM PIN Length Validator
 *
 * The smallest possible building block: length() and a single if / else.
 * No loop needed.
 */
public class PinLengthValidator {

    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN - must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        checkPinLength(pin);
        sc.close();
    }
}
