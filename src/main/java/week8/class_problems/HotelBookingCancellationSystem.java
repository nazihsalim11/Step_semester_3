import java.time.LocalDate;
import java.util.ArrayList;

abstract class Room {

    protected String roomNumber;
    protected double pricePerNight;

    public Room(
            String roomNumber,
            double pricePerNight) {

        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
    }

    public abstract double calculatePrice(
            LocalDate start,
            LocalDate end);

    public String getRoomNumber() {
        return roomNumber;
    }
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber, 150);
    }

    @Override
    public double calculatePrice(
            LocalDate start,
            LocalDate end) {

        long nights =
                end.toEpochDay()
                - start.toEpochDay();

        return nights * pricePerNight;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, 200);
    }

    @Override
    public double calculatePrice(
            LocalDate start,
            LocalDate end) {

        long nights =
                end.toEpochDay()
                - start.toEpochDay();

        return nights * pricePerNight;
    }
}

class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber) {
        super(roomNumber, 300);
    }

    @Override
    public double calculatePrice(
            LocalDate start,
            LocalDate end) {

        long nights =
                end.toEpochDay()
                - start.toEpochDay();

        return nights * pricePerNight;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {

    private Room room;
    private Customer customer;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean cancelled;

    public Reservation(
            Room room,
            Customer customer,
            LocalDate startDate,
            LocalDate endDate) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancelled = false;
    }

    public boolean overlaps(
            LocalDate start,
            LocalDate end) {

        if (cancelled) {
            return false;
        }

        return start.isBefore(endDate)
                && end.isAfter(startDate);
    }

    public void cancel() {
        cancelled = true;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

class BookingManager {

    private ArrayList<Reservation> reservations;

    public BookingManager() {
        reservations = new ArrayList<>();
    }

    public boolean isAvailable(
            Room room,
            LocalDate start,
            LocalDate end) {

        for (Reservation reservation :
                reservations) {

            if (reservation.getRoom()
                    .getRoomNumber()
                    .equals(room.getRoomNumber())) {

                if (reservation.overlaps(
                        start,
                        end)) {

                    return false;
                }
            }
        }

        return true;
    }

    public Reservation bookRoom(
            Room room,
            Customer customer,
            LocalDate start,
            LocalDate end) {

        if (!isAvailable(room, start, end)) {

            System.out.println(
                    "Booking failed: "
                    + room.getRoomNumber()
                    + " is not available for "
                    + start
                    + " to "
                    + end
            );

            return null;
        }

        Reservation reservation =
                new Reservation(
                        room,
                        customer,
                        start,
                        end
                );

        reservations.add(reservation);

        System.out.println(
                room.getRoomNumber()
                + " booked from "
                + start
                + " to "
                + end
        );

        System.out.printf(
                "Total price: $%.2f%n",
                room.calculatePrice(
                        start,
                        end)
        );

        return reservation;
    }

    public void cancelReservation(
            Reservation reservation,
            LocalDate cancellationDate) {

        if (reservation == null) {
            return;
        }

        if (cancellationDate
                .isBefore(reservation.getStartDate())) {

            reservation.cancel();

            System.out.println(
                    "Reservation for "
                    + reservation.getRoom()
                    .getRoomNumber()
                    + " cancelled successfully."
            );

        } else {

            System.out.println(
                    "Cancellation deadline has passed."
            );
        }
    }
}

public class HotelBookingCancellationSystem {

    public static void main(String[] args) {

        BookingManager manager =
                new BookingManager();

        Customer customer =
                new Customer("John Doe");

        Room deluxe =
                new DeluxeRoom("Deluxe Room 101");

        Room standard =
                new StandardRoom("Standard Room 205");

        LocalDate start1 =
                LocalDate.of(2024, 12, 1);

        LocalDate end1 =
                LocalDate.of(2024, 12, 5);

        LocalDate start2 =
                LocalDate.of(2024, 12, 3);

        LocalDate end2 =
                LocalDate.of(2024, 12, 7);

        Reservation r1 =
                manager.bookRoom(
                        deluxe,
                        customer,
                        start1,
                        end1
                );

        manager.bookRoom(
                standard,
                customer,
                start2,
                end2
        );

        // Overlapping reservation.
        manager.bookRoom(
                deluxe,
                customer,
                start2,
                end2
        );

        // Cancel before check-in.
        manager.cancelReservation(
                r1,
                LocalDate.of(2024, 11, 25)
        );

        // Now the room is available again.
        manager.bookRoom(
                deluxe,
                customer,
                start2,
                end2
        );
    }
}
