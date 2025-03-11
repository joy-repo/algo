The Factory Pattern is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. It helps in managing object creation by encapsulating it in a separate method, class, or interface.

## When to Use the Factory Pattern?
* When the exact type of object that needs to be created is determined at runtime.
* When the object creation logic is complex and should be separated from the main business logic.
* When you want to decouple object creation from the client code.
* When multiple subclasses share a common interface, but you want to instantiate them dynamically based on conditions.

## Structure of Factory Pattern
1.	Product (Interface or Abstract Class): Defines the common behavior of objects.
2.	Concrete Products (Subclasses): Implement the Product interface.
3.	Factory (Creator Class): Contains a method to create and return objects of Product.
4.	Client: Calls the factory instead of instantiating objects directly.

```java
// Step 1: Create an interface (Product)
interface Shape {
    void draw();
}

// Step 2: Concrete implementations (Concrete Products)
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

// Step 3: Factory class (Creator)
class ShapeFactory {
    // Factory method to create objects
    public static Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}

// Step 4: Client code
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.getShape("CIRCLE");
        shape1.draw();  // Output: Drawing a Circle

        Shape shape2 = ShapeFactory.getShape("SQUARE");
        shape2.draw();  // Output: Drawing a Square
    }
}
```

## Real-World Examples
* JDBC Driver Manager: DriverManager.getConnection() returns different database connections.
* Logger Frameworks: Factories create different logger instances based on configuration.
* GUI Libraries: Abstract factories create UI elements like buttons and text boxes.
