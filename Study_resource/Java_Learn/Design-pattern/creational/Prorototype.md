
The Prototype Pattern is a creational design pattern that allows objects to be copied without making their classes dependent on how they are instantiated. Instead of creating new instances from scratch, it clones an existing object.


**When to Use the Prototype Pattern?**

✅ When object creation is expensive (e.g., complex initialization or database calls).</br>
✅ When you need to avoid the overhead of creating new instances manually.</br>
✅ When your system should be independent of the exact classes used.</br>
✅ When the number of different object types is dynamic and unknown at compile time.</br>

**Structure of Prototype Pattern**
1.	Prototype (Interface or Abstract Class): Declares a clone() method.
2.	Concrete Prototype: Implements the clone() method for copying itself.
3.	Client: Uses the prototype to create new objects by copying existing ones.


```java
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
public class PrototypePatternDemo {
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
```

## Real-World Examples
* Document Editors (Copy-Paste feature): Creating new documents from a template.
* Video Games (Character Cloning): Creating multiple NPCs with slight variations.
* Prototype Cells in Biology (Cloning organisms with similar DNA).
* Java’s Object.clone() Method: Many Java classes implement Cloneable.

