class EventTicket {
    protected double basePrice;
    protected double amountPaid;

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

    public String printTicket() {
        return "Standard | Balance: "
                + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(
            double basePrice,
            String track) {

        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: "
                + track
                + " | Balance: "
                + getBalanceDue();
    }
}

public class NightlyTicketAnnouncer {

    static String batchPrint(
            EventTicket[] tickets) {

        StringBuilder result =
                new StringBuilder();

        for (EventTicket ticket : tickets) {

            result.append(
                    ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                result.append(
                        " [Track via downcast: "
                        + workshop.getTrack()
                        + "]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {
                new EventTicket(500),
                new WorkshopTicket(
                        1200,
                        "AI/ML")
        };

        System.out.println(
                batchPrint(tickets));
    }
}
