class RaceEntry {

    protected double entryFee;
    protected double amountPaid;

    private double[] lateFeeHistory =
            new double[10];

    private int lateFeeCount = 0;

    public RaceEntry(
            String bibNumber,
            double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.length() < 4) {

            throw new IllegalArgumentException();
        }

        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    protected void applyLateFee(double amount) {

        entryFee += amount;

        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    public double[] getLateFeeHistory() {

        double[] copy =
                new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}

class RunnerEntry extends RaceEntry {

    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalPenaltyAuditTrail {

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K");

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
                r.getBalanceDue());

        double[] history =
                r.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(
                r.getLateFeeHistory()[0]);
    }
}
