package week2.assigment_problems;

import java.util.Scanner;

/**
 * Week 2 Assignment - Problem 3 : Product Inventory CSV Parser
 *
 * Same idea as the student record parser: split on commas, then check that the
 * expected number of fields actually arrived.
 */
public class InventoryRecordParser {

    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        // Exactly three fields expected: ProductName, SKU, Quantity
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0].trim()
                + " | SKU: " + fields[1].trim()
                + " | Qty: " + fields[2].trim());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV line (ProductName,SKU,Quantity): ");
        String line = sc.nextLine();

        parseInventoryRecord(line);
        sc.close();
    }
}
