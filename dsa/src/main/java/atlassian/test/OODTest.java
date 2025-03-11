package atlassian.test;

public class OODTest {
}

// Step 1: Prototype Interface
interface Prototype {
    Prototype clone();
}

// Step 2: Concrete Prototype (Shape)
abstract class Shape implements Prototype {
    int x, y;
    String color;

    public Shape() {} // Default constructor

    public Shape(Shape source) { // Copy constructor
        if (source != null) {
            this.x = source.x;
            this.y = source.y;
            this.color = source.color;
        }
    }

    @Override
    public abstract Shape clone();
}

// Step 3: Concrete Implementations
class Circle extends Shape {
    int radius;

    public Circle() {}

    public Circle(Circle source) {
        super(source);
        if (source != null) {
            this.radius = source.radius;
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius + " and color " + color);
    }
}

class Rectangle extends Shape {
    int width, height;

    public Rectangle() {}

    public Rectangle(Rectangle source) {
        super(source);
        if (source != null) {
            this.width = source.width;
            this.height = source.height;
        }
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    public void draw() {
        System.out.println("Drawing a Rectangle with width " + width + " and height " + height + " and color " + color);
    }
}

// Step 4: Client Code
 class PrototypePatternDemo {
    public static void main(String[] args) {
        Circle originalCircle = new Circle();
        originalCircle.x = 10;
        originalCircle.y = 20;
        originalCircle.radius = 5;
        originalCircle.color = "Red";

        // Clone the original object
        Circle clonedCircle = (Circle) originalCircle.clone();
        clonedCircle.color = "Blue"; // Modify the cloned object

        // Draw both objects
        originalCircle.draw();  // Output: Drawing a Circle with radius 5 and color Red
        clonedCircle.draw();    // Output: Drawing a Circle with radius 5 and color Blue
    }
}
