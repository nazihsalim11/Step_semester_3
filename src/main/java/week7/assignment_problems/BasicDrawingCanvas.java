abstract class Shape {

    private static int shapeCounter = 0;

    private final String shapeId;

    protected double scaleX = 1.0;
    protected double scaleY = 1.0;

    public Shape() {
        shapeCounter++;
        shapeId = "SH-" + (1000 + shapeCounter);
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scaleX *= factor;
        scaleY *= factor;
    }

    public void scale(double xFactor, double yFactor) {
        scaleX *= xFactor;
        scaleY *= yFactor;
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI
                * radius
                * radius
                * scaleX
                * scaleY;
    }
}

class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side
                * side
                * scaleX
                * scaleY;
    }
}

public class BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println(
                s.calculateArea()
        );
    }

    public static void main(String[] args) {

        CircleShape c =
                new CircleShape(5.0);

        System.out.println(
                c.calculateArea()
        );

        SquareShape sq =
                new SquareShape(4.0);

        System.out.println(
                sq.calculateArea()
        );

        sq.scale(2.0);

        System.out.println(
                sq.calculateArea()
        );

        printArea(c);

        System.out.println(
                c.getShapeId()
        );

        System.out.println(
                sq.getShapeId()
        );

        // This will NOT compile:
        // Shape s = new Shape();
    }
}
