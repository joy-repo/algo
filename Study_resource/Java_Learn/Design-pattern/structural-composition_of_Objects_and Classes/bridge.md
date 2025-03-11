The Bridge Pattern is a structural design pattern that decouples abstraction from its implementation so that the two can evolve independently.

## When to Use the Bridge Pattern?

✅ When you want to decouple abstraction from implementation. </br>
✅ When you have multiple dimensions of variation in your classes. </br>
✅ When you want to extend functionality without modifying existing code. </br>
✅ When you need platform-independent implementations (e.g., GUI frameworks for different OS). </br>

## Structure of Bridge Pattern
1.	Abstraction → Defines a high-level interface and maintains a reference to the implementation.
2.	Refined Abstraction → Extends abstraction but delegates work to the implementation.
3.	Implementation (Bridge Interface) → Defines the operations that concrete implementations must provide.
4.	Concrete Implementations → Provide specific implementations of the bridge interface.

**Scenario: Suppose we have different shapes (Circle, Square), and each shape can have different colors (Red, Blue).
Instead of creating multiple combinations (e.g., RedCircle, BlueCircle, RedSquare, BlueSquare), we use Bridge Pattern to separate Shape and Color.**

```java

// Bridge Interface: Color
interface Color {
    void applyColor();
}

// Concrete Implementations
class Red implements Color {
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}

class Blue implements Color {
    public void applyColor() {
        System.out.println("Applying Blue Color");
    }
}

// Abstract class Shape has a reference to Color (Bridge)
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw(); // Abstract method
}


// Refined Abstraction 1
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    public void draw() {
        System.out.print("Drawing Circle - ");
        color.applyColor();
    }
}

// Refined Abstraction 2
class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    public void draw() {
        System.out.print("Drawing Square - ");
        color.applyColor();
    }
}
```

## Real-World Examples
 
* GUI Toolkits → Separating UI components from platform-specific rendering (Swing, Qt).
* Database Drivers → JDBC bridges different databases with a common interface.
* Operating Systems → Windows/Linux/Mac abstraction for file systems.