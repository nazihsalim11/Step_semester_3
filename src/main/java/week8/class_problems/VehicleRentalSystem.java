abstract class Vehicle {

    protected String vehicleName;
    protected boolean available;

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
        this.available = true;
    }

    public abstract double calculateCharge(int days);

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class StandardCar extends Vehicle {

    public StandardCar(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50.0;
    }
}

class LuxuryCar extends Vehicle {

    public LuxuryCar(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100.0;
    }
}

class SUV extends Vehicle {

    public SUV(String vehicleName) {
        super(vehicleName);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 75.0;
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

class Rental {

    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCharge;
    private boolean active;

    public Rental(
            Customer customer,
            Vehicle vehicle,
            int days) {

        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;

        totalCharge =
                vehicle.calculateCharge(days);

        active = true;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void closeRental() {
        active = false;
        vehicle.setAvailable(true);
    }
}

class RentalService {

    public Rental rentVehicle(
            Customer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {

            System.out.println(
                    vehicle.getVehicleName()
                    + " is not available."
            );

            return null;
        }

        vehicle.setAvailable(false);

        Rental rental =
                new Rental(
                        customer,
                        vehicle,
                        days
                );

        System.out.println(
                vehicle.getVehicleName()
                + " rented for "
                + days
                + " days."
        );

        System.out.printf(
                "Total charge: $%.2f%n",
                rental.getTotalCharge()
        );

        return rental;
    }

    public void returnVehicle(
            Rental rental) {

        if (rental == null) {
            return;
        }

        Vehicle vehicle =
                rental.getVehicle();

        rental.closeRental();

        System.out.println(
                vehicle.getVehicleName()
                + " returned. Now available."
        );
    }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Customer customer =
                new Customer("John Doe");

        Vehicle luxury =
                new LuxuryCar(
                        "Luxury Car A"
                );

        Vehicle standard =
                new StandardCar(
                        "Standard Car B"
                );

        RentalService service =
                new RentalService();

        Rental rental1 =
                service.rentVehicle(
                        customer,
                        luxury,
                        3
                );

        Rental rental2 =
                service.rentVehicle(
                        customer,
                        standard,
                        5
                );

        // Try renting the same vehicle again.
        service.rentVehicle(
                customer,
                luxury,
                2
        );

        service.returnVehicle(rental1);

        // Now Luxury Car A can be rented again.
        service.rentVehicle(
                customer,
                luxury,
                2
        );
    }
}
