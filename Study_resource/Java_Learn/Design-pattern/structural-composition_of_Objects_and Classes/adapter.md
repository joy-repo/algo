The Adapter Pattern is a structural design pattern that allows objects with incompatible interfaces to work together. It acts as a bridge between different systems by converting one interface into another.


## When to Use the Adapter Pattern?

✅ When you have an existing class but its interface does not match what the client expects.</br>
✅ When you need to reuse old components in a new system without modifying them.</br>
✅ When working with third-party libraries that have a different interface.</br>

## Types of Adapter Pattern
1.	Class Adapter (Uses Inheritance - “extends”)
2.	Object Adapter (Uses Composition - “has a”) (More Flexible & Preferred)

```java

// Media Player interface (Expected by new system)
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// This is an existing media player but it only plays MP3
class OldMediaPlayer {
    void playMP3(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}


// Adapter class that allows OldMediaPlayer to be used in the new system
class MediaAdapter implements MediaPlayer {
    private OldMediaPlayer oldPlayer = new OldMediaPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("MP3")) {
            oldPlayer.playMP3(fileName);  // Converting request to old format
        } else {
            System.out.println("Format not supported.");
        }
    }
}
// DEMO
public class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();

        player.play("MP3", "song.mp3");  // Output: Playing MP3 file: song.mp3
        player.play("MP4", "video.mp4"); // Output: Format not supported.
    }
}
```