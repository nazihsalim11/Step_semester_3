package week2.class_problems;

import java.util.Scanner;

/**
 * Week 2 Practice - Problem 3 : File Extension Validator
 *
 * lastIndexOf('.') is used, not indexOf, because a filename like
 * "report.final.pdf" must be judged by its LAST dot.
 */
public class FileExtensionValidator {

    static String validateFileExtension(String filename) {
        int dotPosition = filename.lastIndexOf('.');

        // -1 means there is no dot at all; a dot at the very end leaves nothing after it
        if (dotPosition == -1 || dotPosition == filename.length() - 1) {
            return "Rejected - invalid file type";
        }

        String extension = filename.substring(dotPosition + 1);

        if (extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("docx")
                || extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }

        return "Rejected - invalid file type";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        System.out.println(validateFileExtension(filename));
        sc.close();
    }
}
