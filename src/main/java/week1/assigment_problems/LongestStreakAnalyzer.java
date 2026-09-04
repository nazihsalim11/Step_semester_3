package week1.assigment_problems;

/**
 * Problem 3 - The Traffic Signal Streak Analyzer
 *
 * Single pass: keep the length of the streak running right now, and separately
 * keep the best streak seen so far.
 */
public class LongestStreakAnalyzer {

    static void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) {
            System.out.println("No readings in the log");
            return;
        }

        char bestColor = signalLog.charAt(0);
        int bestLength = 1;

        char currentColor = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentColor) {
                currentLength++;
            } else {
                currentColor = signalLog.charAt(i);
                currentLength = 1;        // a new streak starts here
            }

            // Strictly greater, so the EARLIEST longest streak is kept on a tie
            if (currentLength > bestLength) {
                bestLength = currentLength;
                bestColor = currentColor;
            }
        }

        System.out.println("Longest Streak: '" + bestColor + "' repeated " + bestLength + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
