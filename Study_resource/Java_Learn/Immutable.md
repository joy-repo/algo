
## Steps to Make a Class Immutable in Java
To create an immutable class in Java, follow these best practices:

1. Declare the class as final
This prevents subclasses from overriding methods and modifying behavior.

2. Make all fields private and final
This ensures that fields cannot be directly modified after object creation.

3. Do not provide setter methods
Setters allow modifications, which must be avoided.

4. Initialize fields only in the constructor
The constructor should fully initialize all fields.

5. Ensure deep copies of mutable objects
If a field is a mutable object (like an array, List, or Date), return a deep copy instead of the original reference.

6. Implement readResolve after Extending Serializable.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutablePerson implements Serializable {
    private final String name;
    private final int age;
    private final List<String> hobbies; // Mutable object

    // Constructor
    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // Create a defensive copy to prevent modifications
        this.hobbies = new ArrayList<>(hobbies);
    }

    // Getters with no setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    protected Object readResolve() {
        return this;
    }

    // Return an unmodifiable list instead of the original reference
    public List<String> getHobbies() {
        return Collections.unmodifiableList(hobbies);
    }

    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");
        hobbies.add("Gaming");

        ImmutablePerson person = new ImmutablePerson("John Doe", 30, hobbies);
        System.out.println(person.getName()); // John Doe
        System.out.println(person.getAge()); // 30
        System.out.println(person.getHobbies()); // [Reading, Gaming]

        // Attempting to modify hobbies externally
        // This will throw UnsupportedOperationException
        person.getHobbies().add("Swimming");
    }
}

```