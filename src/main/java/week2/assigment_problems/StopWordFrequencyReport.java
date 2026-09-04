package week2.assigment_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Week 2 Assignment - Problem 5 : Stop-Word-Filtered Word Frequency Report
 *
 * Filler words are excluded so the report shows meaningful themes.
 * Results are printed by count, highest first.
 */
public class StopWordFrequencyReport {

    private static final String[] STOP_WORDS = {"the", "was", "and", "a", "is", "of", "in"};

    private static boolean isStopWord(String word) {
        for (int i = 0; i < STOP_WORDS.length; i++) {
            if (STOP_WORDS[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    static void printFilteredWordFrequency(String feedback) {
        // Lowercase everything, then drop the common punctuation marks
        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "");

        String[] words = cleaned.trim().split("\\s+");

        Map<String, Integer> counts = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (word.length() == 0 || isStopWord(word)) {
                continue;
            }

            // Add 1 to the existing count, or start at 1 if this word is new
            if (counts.containsKey(word)) {
                counts.put(word, counts.get(word) + 1);
            } else {
                counts.put(word, 1);
            }
        }

        // Copy the map into two parallel arrays so it can be sorted by hand
        int size = counts.size();
        String[] uniqueWords = new String[size];
        int[] frequencies = new int[size];

        int index = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            uniqueWords[index] = entry.getKey();
            frequencies[index] = entry.getValue();
            index++;
        }

        // Selection sort: repeatedly pull the highest remaining count to the front
        for (int i = 0; i < size - 1; i++) {
            int maxPosition = i;

            for (int j = i + 1; j < size; j++) {
                if (frequencies[j] > frequencies[maxPosition]) {
                    maxPosition = j;
                }
            }

            int tempCount = frequencies[i];
            frequencies[i] = frequencies[maxPosition];
            frequencies[maxPosition] = tempCount;

            String tempWord = uniqueWords[i];
            uniqueWords[i] = uniqueWords[maxPosition];
            uniqueWords[maxPosition] = tempWord;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(uniqueWords[i] + ": " + frequencies[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter feedback text: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);
        sc.close();
    }
}
