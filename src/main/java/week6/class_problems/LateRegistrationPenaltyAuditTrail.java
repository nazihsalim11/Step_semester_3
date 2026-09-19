class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory =
            new double[10];

    private int lateFeeCount = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {

        basePrice += amount;

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

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateRegistrationPenaltyAuditTrail {

    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
                w.getBalanceDue());

        double[] history =
                w.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        System.out.println(
                w.getLateFeeHistory()[0]);
    }
}
