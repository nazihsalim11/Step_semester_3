package week4.class_problems;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty() || !isValidName(passengerName.trim())) {
            throw new IllegalArgumentException("Invalid passenger name");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Ticket already checked in previously.");
        } else {
            this.checkedIn = true;
            System.out.println("Ticket successfully checked in.");
        }
    }

    public String getKey() {
        return passengerName + "||" + destination;
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        String[] acceptedKeys = new String[rawBookings.length];
        int acceptedCount = 0;

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.getKey();
                boolean isDuplicate = false;
                for (int i = 0; i < acceptedCount; i++) {
                    if (acceptedKeys[i].equalsIgnoreCase(key)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate) {
                    duplicates++;
                } else {
                    acceptedKeys[acceptedCount++] = key;
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }
}

public class BusTicketBookingValidator {
    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        BusTicket.processBatch(rawBookings);
    }
}
