package week1.assigment_problems;

/**
 * Problem 2 - The Typing Speed Test Accuracy Checker
 *
 * Both strings are the same length, so one index can be used to read from both.
 * Positions are reported 1-based because that is what a user reads on screen.
 */
public class TypingAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchIndex = -1;   // -1 means "no mismatch seen yet"

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchIndex == -1) {
                firstMismatchIndex = i;   // record only the FIRST one
            }
        }

        double accuracy = (matched * 100.0) / total;
        double roundedAccuracy = Math.round(accuracy * 100.0) / 100.0;

        String report = "Matched: " + matched + "/" + total
                + " | Accuracy: " + roundedAccuracy + "%";

        if (firstMismatchIndex == -1) {
            report = report + " | No Mismatches";
        } else {
            report = report + " | First Mismatch at position " + (firstMismatchIndex + 1)
                    + " ('" + original.charAt(firstMismatchIndex)
                    + "' vs '" + typed.charAt(firstMismatchIndex) + "')";
        }

        System.out.println(report);
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
