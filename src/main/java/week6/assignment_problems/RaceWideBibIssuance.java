class RaceEntry {

    private static int bibCounter = 0;

    private final String entryCode;

    protected double entryFee;
    protected double amountPaid;

    public RaceEntry(
            String bibNumber,
            double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.length() < 4) {

            throw new IllegalArgumentException();
        }

        bibCounter++;

        entryCode =
                "RACE-" + bibCounter;

        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public void pay(
            double amount,
            String mode) {

        System.out.println(
                "Paying via " + mode);

        pay(amount);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public static boolean isValidDiscountCode(
            String code) {

        if (code == null ||
            code.length() != 5) {

            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public String getEntryCode() {
        return entryCode;
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}

public class RaceWideBibIssuance {

    static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + relay
                + " relay | "
                + individual
                + " individual";
    }

    public static void main(String[] args) {

        RaceEntry r =
                new RaceEntry(
                        "BIB1001",
                        500);

        System.out.println(
                r.getEntryCode());

        r.pay(10, "UPI");

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M123A"));

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M12A"));

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "X123A"));

        RaceEntry[] entries = {
                r,
                null,
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4)
        };

        System.out.println(
                RaceEntry.getBibCounter());

        System.out.println(
                settleNight(entries));
    }
}
