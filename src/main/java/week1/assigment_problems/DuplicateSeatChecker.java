package week1.assigment_problems;

/**
 * Problem 1 - The Exam Hall Seat Duplication Checker
 *
 * No Collections allowed: every seat number is compared against every later
 * seat number using a nested loop.
 */
public class DuplicateSeatChecker {

    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundAny = false;

        for (int i = 0; i < seatNumbers.length; i++) {

            // Skip this value if it was already reported earlier in the array
            boolean alreadyReported = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyReported = true;
                    break;
                }
            }
            if (alreadyReported) {
                continue;
            }

            // j starts at i + 1 so each pair is checked once, not twice
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundAny = true;
                    break;
                }
            }
        }

        if (!foundAny) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});
    }
}
