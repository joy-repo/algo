package atlassian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Abstract base class representing a nested element.
abstract class NestedElement {
    /**
     * Returns the integer value if this is a single integer.
     * Otherwise, throws an UnsupportedOperationException.
     */
    public int getInteger() {
        throw new UnsupportedOperationException("Not a single integer");
    }
    
    /**
     * Returns the nested list if this is a composite element.
     * Otherwise, throws an UnsupportedOperationException.
     */
    public List<NestedElement> getList() {
        throw new UnsupportedOperationException("Not a nested list");
    }
}

// Represents a single integer element.
class SingleElement extends NestedElement {
    private final int value;
    
    public SingleElement(int value) {
        this.value = value;
    }
    
    @Override
    public int getInteger() {
        return value;
    }
    
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

// Represents a composite nested list.
class CompositeElement extends NestedElement {
    private final List<NestedElement> list;
    
    public CompositeElement() {
        this.list = new ArrayList<>();
    }
    
    public void add(NestedElement element) {
        list.add(element);
    }
    
    @Override
    public List<NestedElement> getList() {
        return Collections.unmodifiableList(list);
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
}

// Utility class for flattening nested elements.
class NestedFlattener {
    /**
     * Flattens the given NestedElement structure into a list of integers.
     */
    public List<Integer> flatten(NestedElement element) {
        List<Integer> result = new ArrayList<>();
        flattenHelper(element, result);
        return result;
    }
    
    private void flattenHelper(NestedElement element, List<Integer> result) {
        // If this element supports getList(), then it is composite.
        try {
            List<NestedElement> subElements = element.getList();
            for (NestedElement sub : subElements) {
                flattenHelper(sub, result);
            }
        } catch (UnsupportedOperationException e) {
            // Otherwise, it must be a single element.
            result.add(element.getInteger());
        }
    }
}

// Example usage demonstrating the improved design.
public class ImprovedNestedListDemo {
    public static void main(String[] args) {
        // Construct a nested structure: [1, [2, 3], [4, [5, 6]], 7]
        CompositeElement root = new CompositeElement();
        root.add(new SingleElement(1));
        
        CompositeElement list1 = new CompositeElement();
        list1.add(new SingleElement(2));
        list1.add(new SingleElement(3));
        root.add(list1);
        
        CompositeElement list2 = new CompositeElement();
        list2.add(new SingleElement(4));
        CompositeElement list3 = new CompositeElement();
        list3.add(new SingleElement(5));
        list3.add(new SingleElement(6));
        list2.add(list3);
        root.add(list2);
        
        root.add(new SingleElement(7));
        
        NestedFlattener flattener = new NestedFlattener();
        List<Integer> flat = flattener.flatten(root);
        System.out.println("Flattened list: " + flat);
    }
}