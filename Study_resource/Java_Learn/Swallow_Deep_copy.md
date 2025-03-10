

# Swallow Copy

```java
import java.util.ArrayList;
import java.util.List;

class ShallowPerson implements Cloneable {
    String name;
    List<String> hobbies; // Mutable object

    // Constructor
    public ShallowPerson(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    // Overriding clone() for shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Default clone() creates a shallow copy
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");

        ShallowPerson p1 = new ShallowPerson("John", hobbies);
        ShallowPerson p2 = (ShallowPerson) p1.clone();

        // Modifying the original list affects the cloned object
        p1.hobbies.add("Gaming");

        System.out.println(p1.hobbies); // [Reading, Gaming]
        System.out.println(p2.hobbies); // [Reading, Gaming] (same reference)
    }
}

```


✅ clone() creates a new ShallowPerson object. </br>
❌ The hobbies list is not copied, so both p1 and p2 share the same list reference.</br>
❌ Modifying p1.hobbies also changes p2.hobbies (unexpected behavior).</br>

# Deep Copy

```java
import java.util.ArrayList;
import java.util.List;

class DeepPerson implements Cloneable {
    String name;
    List<String> hobbies; // Mutable object

    // Constructor
    public DeepPerson(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = new ArrayList<>(hobbies); // Create a new list (deep copy)
    }

    // Overriding clone() for deep copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepPerson cloned = (DeepPerson) super.clone();
        cloned.hobbies = new ArrayList<>(this.hobbies); // Create a new list copy
        return cloned;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");

        DeepPerson p1 = new DeepPerson("John", hobbies);
        DeepPerson p2 = (DeepPerson) p1.clone();

        // Modifying the original list does NOT affect the cloned object
        p1.hobbies.add("Gaming");

        System.out.println(p1.hobbies); // [Reading, Gaming]
        System.out.println(p2.hobbies); // [Reading] (separate list)
    }
}

```

✅ clone() creates a new DeepPerson object. </br>
✅ hobbies is copied into a new list, ensuring each object has its own reference. </br>
✅ Modifying p1.hobbies does not affect p2.hobbies. </br>