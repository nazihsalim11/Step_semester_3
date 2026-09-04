package week2.class_problems;

import java.util.Scanner;

/**
 * Week 2 Practice - Problem 2 : CSV Student Record Parser
 *
 * split(",") returns an array of fields. Checking its length is what tells us
 * whether the line was well formed.
 */
public class StudentRecordParser {

    static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        // Exactly three fields expected: Name, RollNumber, Department
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + fields[0].trim()
                + " | Roll No: " + fields[1].trim()
                + " | Dept: " + fields[2].trim());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV line (Name,RollNumber,Department): ");
        String line = sc.nextLine();

        parseStudentRecord(line);
        sc.close();
    }
}
