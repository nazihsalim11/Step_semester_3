import java.util.ArrayList;

interface IPaymentMethod {
    boolean pay(double amount);
    String getMethodName();
}

class CreditCardPayment
        implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {

        System.out.println(
                "Payment via Credit Card successful."
        );

        return true;
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment
        implements IPaymentMethod {

    private boolean shouldSucceed;

    public DigitalWalletPayment(
            boolean shouldSucceed) {

        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean pay(double amount) {

        if (shouldSucceed) {

            System.out.println(
                    "Payment via Digital Wallet successful."
            );

            return true;
        }

        System.out.println(
                "Payment via Digital Wallet failed."
        );

        return false;
    }

    @Override
    public String getMethodName() {
        return "Digital Wallet";
    }
}

class CashOnDeliveryPayment
        implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {

        System.out.println(
                "Cash on Delivery selected."
        );

        return true;
    }

    @Override
    public String getMethodName() {
        return "Cash on Delivery";
    }
}

class FoodItem {

    private String name;
    private double price;

    public FoodItem(
            String name,
            double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class LineItem {

    private FoodItem foodItem;
    private int quantity;

    public LineItem(
            FoodItem foodItem,
            int quantity) {

        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public double getTotal() {

        return foodItem.getPrice()
                * quantity;
    }

    public String getDescription() {

        return foodItem.getName()
                + " (Qty "
                + quantity
                + ")";
    }
}

class Restaurant {

    private String name;

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public void notifyCustomer(
            String message) {

        System.out.println(
                "Notification: "
                + message
        );
    }
}

class Order {

    private static int orderCounter = 122;

    private int orderId;
    private Customer customer;
    private Restaurant restaurant;

    private ArrayList<LineItem> lineItems;

    private String status;

    public Order(
            Customer customer,
            Restaurant restaurant) {

        orderCounter++;

        orderId = orderCounter;

        this.customer = customer;
        this.restaurant = restaurant;

        lineItems = new ArrayList<>();

        status = "Created";

        System.out.println(
                "Order created."
        );
    }

    public void addItem(
            FoodItem foodItem,
            int quantity) {

        if (quantity <= 0) {
            return;
        }

        LineItem item =
                new LineItem(
                        foodItem,
                        quantity
                );

        lineItems.add(item);

        System.out.println(
                "Added "
                + item.getDescription()
        );
    }

    private double calculateTotal() {

        double total = 0;

        for (LineItem item : lineItems) {
            total += item.getTotal();
        }

        return total;
    }

    public void placeOrder(
            IPaymentMethod paymentMethod) {

        if (lineItems.isEmpty()) {

            System.out.println(
                    "Cannot place order: "
                    + "Order must contain at least one item."
            );

            return;
        }

        System.out.println(
                "Order placed successfully."
        );

        boolean paymentSuccessful =
                paymentMethod.pay(
                        calculateTotal()
                );

        if (paymentSuccessful) {

            status = "Paid";

            customer.notifyCustomer(
                    "Order #"
                    + orderId
                    + " placed and paid."
            );

        } else {

            status = "Pending Payment";

            customer.notifyCustomer(
                    "Order #"
                    + orderId
                    + " placed, awaiting payment."
            );
        }

        System.out.println(
                "Order status: "
                + status
        );
    }

    public int getOrderId() {
        return orderId;
    }
}

public class FoodOrderFlexiblePaymentSystem {

    public static void main(String[] args) {

        Customer customer =
                new Customer("John Doe");

        Restaurant restaurant =
                new Restaurant(
                        "Pizza Palace"
                );

        FoodItem pizza =
                new FoodItem(
                        "Pizza",
                        12.0
                );

        FoodItem soda =
                new FoodItem(
                        "Soda",
                        3.0
                );

        FoodItem burger =
                new FoodItem(
                        "Burger",
                        10.0
                );

        // First order
        Order order1 =
                new Order(
                        customer,
                        restaurant
                );

        order1.addItem(
                pizza,
                2
        );

        order1.addItem(
                soda,
                1
        );

        // Empty order test is separate.
        Order emptyOrder =
                new Order(
                        customer,
                        restaurant
                );

        emptyOrder.placeOrder(
                new CreditCardPayment()
        );

        order1.placeOrder(
                new CreditCardPayment()
        );

        // Second order
        Order order2 =
                new Order(
                        customer,
                        restaurant
                );

        order2.addItem(
                burger,
                1
        );

        order2.placeOrder(
                new DigitalWalletPayment(false)
        );
    }
}
