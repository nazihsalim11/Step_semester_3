package week1.class_problems;

import java.util.Random;

/**
 * Problem 3 - BMI Calculator for a Team (Corporate Wellness Program)
 *
 * Heights and weights are held in two parallel arrays: index i of each array
 * belongs to the same person.
 */
public class BmiCalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {        // covers 18.5 - 24.9
            return "Normal";
        } else if (bmi < 30) {        // covers 25 - 29.9
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI   | Status");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);

            // Round to 2 decimal places for display only
            double roundedBmi = Math.round(bmi * 100.0) / 100.0;

            System.out.println((i + 1) + "      | " + heights[i]
                    + "       | " + weights[i]
                    + "        | " + roundedBmi
                    + " | " + getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        int people = 10;
        double[] heights = new double[people];
        double[] weights = new double[people];

        Random random = new Random();
        for (int i = 0; i < people; i++) {
            // Height between 1.50 m and 1.95 m
            heights[i] = Math.round((1.50 + random.nextDouble() * 0.45) * 100.0) / 100.0;
            // Weight between 45 kg and 100 kg
            weights[i] = 45 + random.nextInt(56);
        }

        printWellnessReport(heights, weights);
    }
}
