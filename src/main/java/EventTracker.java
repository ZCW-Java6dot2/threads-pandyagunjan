import java.util.HashMap;
import java.util.Map;

public class EventTracker implements Tracker {

    private static EventTracker INSTANCE = new EventTracker();

    private Map<String, Integer> tracker;

    public Map<String, Integer> getTracker() {
        return this.tracker;
    }

    public void setTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }

    private EventTracker() {
        this.tracker = new HashMap<>();
    }

    synchronized public static EventTracker getInstance() {
        return INSTANCE;
    }

    synchronized public void push(String message) {
        Integer x=tracker.getOrDefault(message,0);
        tracker.put(message,x+1);
    }

    synchronized public Boolean has(String message) {
        return tracker.containsKey(message);
    }

    synchronized public void handle(String message, EventHandler e) {
        try {
           // e.handle();
            Integer x=tracker.getOrDefault(message,0);
            if(x==1 && x!=0)
            {
                this.tracker.remove(message);
            }
            else
                this.tracker.put(message,x-1);

        } catch (NullPointerException n) {
            System.out.println("Currently not being tracked.");
        }
    }

    // Do not use this. This constructor is for tests only
    // Using it breaks the singleton class
    EventTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }
}
