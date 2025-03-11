The Decorator Pattern is a structural design pattern that allows dynamically adding behavior to objects without modifying their structure. It provides a flexible alternative to subclassing for extending functionalities.


🔹 When to Use the Decorator Pattern?

✅ When you want to add responsibilities dynamically to objects.
✅ When subclassing leads to too many subclasses.
✅ When you want to extend functionality at runtime instead of compile-time.
✅ When you need open-closed principle compliance (open for extension, closed for modification).


## Structure of Decorator Pattern
1.	Component → Defines the common interface.
2. Concrete Component → The base object that can be decorated.
3.	Decorator (Abstract) → Contains a reference to the component and forwards requests.
4.	Concrete Decorators → Extend behavior by modifying component behavior.

```java

// Component: Defines the common interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component: Basic Coffee
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getCost() {
        return 5.0; // Base price
    }
}

// Decorator: Wraps around Coffee objects
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorator 1: Adds Milk
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.5; // Additional cost for milk
    }
}

// Concrete Decorator 2: Adds Sugar
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5; // Additional cost for sugar
    }
}

```

## Real-World Examples
🔹 Java I/O Streams → BufferedReader(new FileReader("file.txt"))
🔹 GUI Components → Adding scrollbars to windows dynamically
🔹 Text Processing → Applying bold, italic, underline dynamically
🔹 Burger Customization → Adding cheese, lettuce, etc., at runtime
