
The Abstract Factory Pattern is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. It is an extension of the Factory Pattern, allowing the creation of multiple factories for different product families.

## When to Use

✅ When you need to create families of related objects. </br>
✅ When you want to enforce consistency among objects. </br>
✅ When the exact classes used need to be abstracted from the client.</br>
✅ When the system should be configured with multiple product variants.</br>

## Structure of Abstract Factory Pattern
1.	**Abstract Product:** Common interface for a family of related objects.
2.	**Concrete Products:** Implementations of abstract products.
3.	**Abstract Factory:** Interface defining methods to create related objects.
4.	**Concrete Factory:** Implements the abstract factory and creates specific product variants.
5.	**Client:** Uses the factory to create products without knowing their exact type.

```java
// Step 1: Abstract Products (Common interface for different variants)
interface Button {
    void paint();
}

interface Checkbox {
    void render();
}

// Step 2: Concrete Products (Windows implementations)
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a Windows-style button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering a Windows-style checkbox");
    }
}

// Step 3: Concrete Products (Mac implementations)
class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering a Mac-style button");
    }
}

class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering a Mac-style checkbox");
    }
}

// Step 4: Abstract Factory (Defines methods for object creation)
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Step 5: Concrete Factories (Implementation of abstract factory)
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Step 6: Client Code (Uses the factory without depending on concrete classes)
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // We can change this dynamically
        GUIFactory factory = new WindowsFactory();
        // GUIFactory factory = new MacFactory();

        Button button = factory.createButton();
        button.paint();  // Output: Rendering a Windows-style button

        Checkbox checkbox = factory.createCheckbox();
        checkbox.render();  // Output: Rendering a Windows-style checkbox
    }
}
```

