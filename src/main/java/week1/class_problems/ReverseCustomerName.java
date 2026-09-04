package week1.class_problems;

import java.util.Scanner;

/**
 * Problem 5 - Reverse Customer Name (Customer Identity Verification)
 *
 * The original string is never modified - Strings in Java are immutable, so the
 * reversal has to be built as a brand new value.
 */
public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {
        String reversed = "";
        // Walk backwards through the name, adding one character at a time
        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed = reversed + customerName.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reverseCustomerName(customerName));

        sc.close();
    }
}
