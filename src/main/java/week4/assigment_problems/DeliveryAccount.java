package week4.assigment_problems;
public class DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0) {
            throw new IllegalArgumentException();
        }

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException();
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0;

        int first = Math.min(delayMinutes, 5);
        fee += orderValue * 0.005 * first;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes - 5, 10);
            fee += orderValue * 0.01 * second;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += orderValue * 0.02 * third;
        }

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        return Math.max(fee, minimumFee);
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        double surgeFee =
                account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            surgeFee = surgeFee * 0.5;
        }

        System.out.println("Surge fee: " + surgeFee);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts.length != amounts.length ||
                accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException();
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double totalSurgeFees = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double surgeFee =
                    accounts[i].calculateSurgeFee(
                            delayMinutesArray[i]);

            if (accounts[i] instanceof Premium) {
                surgeFee = surgeFee * 0.5;
                premium++;
            } else {
                regular++;
            }

            totalSurgeFees += surgeFee;
            processed++;
        }

        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(premium + " premium");
        System.out.println(regular + " regular");
        System.out.println("grand total surge fees = " +
                totalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new Premium("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
                500,
                400,
                300
        };

        int[] delayMinutesArray = {
                10,
                5,
                0
        };

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

class Premium extends DeliveryAccount {

    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}
