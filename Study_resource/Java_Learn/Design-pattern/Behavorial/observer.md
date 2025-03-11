The Observer Pattern is a behavioral design pattern that establishes a one-to-many relationship between objects. When the subject (observable) changes its state, all dependent observers are automatically notified.


##  When to Use the Observer Pattern?

✅ When one object’s state changes, and multiple objects need to be notified automatically.
✅ When you want to implement event-driven programming (e.g., GUI event listeners).
✅ When there is a publisher-subscriber relationship between objects.
✅ When you want to reduce coupling between objects.

##  Structure of Observer Pattern
1.	Subject (Observable) → Maintains a list of observers and notifies them of state changes.
2. Concrete Subject → Implements the subject and manages observers.
3. Observer → Defines an interface for receiving updates.
4. Concrete Observers → Implement the observer interface and update their state when notified.

```java

// Observer: Defines the update method
interface Observer {
    void update(float temperature);
}


// Subject: Defines methods for attaching, detaching, and notifying observers
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}



// Concrete Subject: Weather Station
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers(); // Notify observers when the state changes
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}


// Concrete Observer 1: Mobile Display
class MobileDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Mobile Display: Temperature updated to " + temperature + "°C");
    }
}

// Concrete Observer 2: LED Display
class LEDDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("LED Display: Temperature updated to " + temperature + "°C");
    }
}


/// DEMO
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer mobileDisplay = new MobileDisplay();
        Observer ledDisplay = new LEDDisplay();

        weatherStation.addObserver(mobileDisplay);
        weatherStation.addObserver(ledDisplay);

        weatherStation.setTemperature(25.0f); // Updates both displays
        weatherStation.setTemperature(30.5f); // Updates both displays again
    }
}
```

## Real-World Examples

🔹 Event Listeners in GUI → Button click event notifies all listeners.
🔹 Stock Market → Investors (observers) are notified when stock prices change.
🔹 News Subscription → Users get notified when a new article is published.
🔹 Social Media Notifications → Users get notified when someone they follow posts.