package week4.class_problems;

import java.util.Arrays;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Total fare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        long totalCents = Math.round(totalFare * 100);
        long baseCents = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        double[] breakdown = new double[passengerCount];
        for (int i = 0; i < passengerCount; i++) {
            long share = baseCents;
            // The extra paisa is absorbed by the last share(s)
            if (i >= passengerCount - remainder) {
                share += 1;
            }
            breakdown[i] = share / 100.0;
        }
        return breakdown;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class RemainderFairFareSplitter {
    public static void main(String[] args) {
        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(f1.fareBreakdown()));

        FareSplitter f2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}
