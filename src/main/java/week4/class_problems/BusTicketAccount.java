package week4.class_problems;
public class BusTicketAccount {
    protected String bookingId;
    protected double ticketFare;

    static double penaltyRate;

    static {
        penaltyRate = 1.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException();
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException();
        }

        return ticketFare * penaltyRate / 100 * minutesLate;
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof Sleeper) {
            penalty = penalty * 0.5;
        }

        System.out.println("Penalty: " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
                accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException();
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double totalPenalty = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            if (accounts[i] instanceof Sleeper) {
                penalty = penalty * 0.5;
                sleeper++;
            } else {
                regular++;
            }

            totalPenalty += penalty;
            processed++;
        }

        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(sleeper + " sleeper");
        System.out.println(regular + " regular");
        System.out.println("grand total penalties = " + totalPenalty);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new Sleeper("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLate = {
                10,
                5,
                0
        };

        processBatch(accounts, amounts, minutesLate);
    }
}

class Sleeper extends BusTicketAccount {

    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}
