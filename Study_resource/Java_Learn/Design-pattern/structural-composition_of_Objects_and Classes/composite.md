The Composite Pattern is a structural design pattern that allows treating individual objects and groups of objects (compositions) uniformly. It organizes objects into a tree structure, enabling clients to work with both single objects and compositions the same way.



## When to Use the Composite Pattern?

✅ When you need to represent part-whole hierarchies. </br>
✅ When individual objects and groups should be treated uniformly. </br>
✅ When you want to simplify client code by handling complex tree structures as single entities. </br>
✅ When you need to perform operations recursively on a tree structure. </br>

## Structure of Composite Pattern

1.	Component → Defines the common interface for both leaf and composite nodes.
2.	Leaf → Represents individual objects (cannot have children).
3.	Composite → Contains child components (can be leaves or other composites).
4.	Client → Works with both individual and composite objects uniformly.



## Scenario: We want to model a file system where both File and Folder should be handled uniformly.



```java

// Component: Defines a common interface for both files and folders
interface FileSystem {
    void showDetails();
}


// Leaf: Represents individual files
class File implements FileSystem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}



// Composite: Can contain files or other folders
class Folder implements FileSystem {
    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystem component) {
        children.add(component);
    }

    public void remove(FileSystem component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystem component : children) {
            component.showDetails();  // Recursively calls showDetails()
        }
    }
}



```