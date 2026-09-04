package week1.assigment_problems;

/**
 * Problem 5 - The Movie Review Word Length Profiler
 *
 * Short = 1-4 letters, Medium = 5-8 letters, Long = 9 or more.
 */
public class WordLengthProfiler {

    static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");   // \\s+ handles double spaces too

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (int i = 0; i < words.length; i++) {
            int length = words[i].length();

            if (length == 0) {
                continue;
            } else if (length <= 4) {
                shortCount++;
            } else if (length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount
                + " | Medium: " + mediumCount
                + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
