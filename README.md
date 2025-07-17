# JukeBox Music System 🎵📻

A Java-based music management system that simulates a digital jukebox using custom data structures. Features album management with LIFO ordering, queue-based song playback with FIFO ordering, and shuffle functionality for an authentic jukebox experience.

## 🌟 Features

- **Album Management** - Create and manage music albums with LIFO song ordering
- **Queue-Based Playback** - FIFO song queue system for sequential playback
- **Shuffle Functionality** - Randomize song order in the jukebox queue
- **Capacity Management** - Fixed-capacity jukebox with overflow handling
- **Duplicate Prevention** - Automatic detection and prevention of duplicate songs
- **Custom Data Structures** - LinkedStack and LinkedQueue implementations
- **Comprehensive Testing** - Full test suite for all functionality

## 🚀 Quick Start

### Prerequisites

- Java 8 or higher
- Basic understanding of data structures (stacks, queues, linked lists)

### Installation

1. **Download the project files:**
```bash
git clone https://github.com/yourusername/jukebox-music-system.git
cd jukebox-music-system
```

2. **Compile the project:**
```bash
javac *.java
```

3. **Run the tests:**
```bash
java JukeBoxTester
```

4. **Expected output:**
```
testStackAdd: PASS
testStackRemove: PASS
testQueueAdd: PASS
testQueueRemove: PASS
testPeek: PASS
testContains: PASS
testGetList: PASS
testAddSongToAlbum: PASS
testRemoveSongFromAlbum: PASS
testAlbumToString: PASS
testAddSongToJukebox: PASS
testAddAlbumToJukebox: PASS
testPlaySongFromJukebox: PASS
testJukeboxShuffle: PASS
ALL TESTS: PASS
```

## 🏗️ System Architecture

### Core Classes

#### 1. **Song.java** - Basic Music Unit
```java
public class Song {
    private String name;        // Song title
    private String artist;      // Artist name
    private Album album;        // Associated album
    
    public String toString();   // "Name: Artist (Album)"
    public boolean equals(Object o); // Case-insensitive comparison
}
```

#### 2. **Album.java** - Song Collection (LIFO)
```java
public class Album {
    private String albumName;           // Album title
    private int size;                   // Number of songs
    private LinkedStack<Song> trackList; // LIFO song storage
    
    public void addSong(Song s);        // Add song to top
    public Song removeSong();           // Remove from top
    public Song firstSong();            // Peek at top song
}
```

#### 3. **JukeBox.java** - Playback Queue (FIFO)
```java
public class JukeBox {
    private int capacity;               // Maximum songs allowed
    private LinkedQueue<Song> songQueue; // FIFO playback queue
    
    public void addSongToQueue(Song song);     // Add single song
    public void addAlbumToQueue(Album album);  // Add entire album
    public Song playSong();                    // Play next song
    public void shuffleSongQueue();            // Randomize order
}
```

#### 4. **LinkedStack.java** - LIFO Data Structure
```java
public class LinkedStack<T> implements StackADT<T> {
    private LinkedNode<T> top;          // Top of stack
    
    public void push(T value);          // Add to top
    public T pop();                     // Remove from top
    public T peek();                    // View top without removing
    public boolean contains(T value);   // Search for element
}
```

#### 5. **LinkedQueue.java** - FIFO Data Structure
```java
public class LinkedQueue<T> implements QueueADT<T> {
    private LinkedNode<T> front;        // Front of queue
    private LinkedNode<T> back;         // Back of queue
    private int size;                   // Current size
    
    public void enqueue(T value);       // Add to back
    public T dequeue();                 // Remove from front
    public T peek();                    // View front without removing
}
```

## 🎮 How the System Works

### Album Management (LIFO - Last In, First Out)

#### Creating and Managing Albums
```java
// Create new album
Album album = new Album("Greatest Hits");

// Add songs (LIFO order)
album.addSong(new Song("Song A", "Artist 1")); // Bottom of stack
album.addSong(new Song("Song B", "Artist 1")); // Middle of stack
album.addSong(new Song("Song C", "Artist 1")); // Top of stack

// Remove songs (LIFO order)
Song removed = album.removeSong(); // Returns "Song C" (most recent)
```

### JukeBox Queue Management (FIFO - First In, First Out)

#### Basic Queue Operations
```java
// Create jukebox with capacity of 5 songs
JukeBox jukebox = new JukeBox(5);

// Add individual songs
jukebox.addSongToQueue(new Song("Rock Song", "Rock Artist"));
jukebox.addSongToQueue(new Song("Pop Song", "Pop Artist"));

// Play songs in order added
Song playing = jukebox.playSong(); // Returns "Rock Song" (first added)
```

#### Adding Albums to JukeBox
```java
// Album with 3 songs (A, B, C in LIFO order)
Album album = new Album("Test Album");
album.addSong(new Song("Song A", "Artist")); // Added first
album.addSong(new Song("Song B", "Artist")); // Added second
album.addSong(new Song("Song C", "Artist")); // Added third (top)

// Add album to jukebox (preserves original order)
jukebox.addAlbumToQueue(album);
// Queue now contains: [Song A, Song B, Song C] in FIFO order
```

## 📊 Data Structure Details

### Stack Implementation (LIFO)

#### Structure
```
    TOP
     ↓
[Song C] → [Song B] → [Song A] → null
```

#### Operations
```java
// Push operation
public void push(T value) {
    LinkedNode<T> newNode = new LinkedNode<T>(value);
    newNode.setNext(top);    // Link to current top
    top = newNode;           // Update top pointer
}

// Pop operation
public T pop() {
    if (isEmpty()) return null;
    LinkedNode<T> toPop = top;
    top = top.getNext();     // Move top to next node
    return toPop.getData();
}
```

### Queue Implementation (FIFO)

#### Structure
```
FRONT                    BACK
  ↓                        ↓
[Song A] → [Song B] → [Song C] → null
```

#### Operations
```java
// Enqueue operation
public void enqueue(T value) {
    LinkedNode<T> newNode = new LinkedNode<T>(value);
    if (isEmpty()) {
        front = back = newNode;  // First element
    } else {
        back.setNext(newNode);   // Link to current back
        back = newNode;          // Update back pointer
    }
}

// Dequeue operation
public T dequeue() {
    if (isEmpty()) return null;
    LinkedNode<T> toDequeue = front;
    front = front.getNext();     // Move front to next node
    if (front == null) back = null; // Handle empty queue
    return toDequeue.getData();
}
```

## 🔧 Configuration

### Jukebox Settings
```java
// Create jukebox with different capacities
JukeBox smallJukebox = new JukeBox(3);   // 3 song limit
JukeBox largeJukebox = new JukeBox(100); // 100 song limit

// Check capacity status
boolean isFull = jukebox.isFull();
boolean isEmpty = jukebox.isEmpty();
int currentSize = jukebox.size();
int maxCapacity = jukebox.capacity();
```

### Duplicate Prevention
```java
// Attempting to add duplicate song throws exception
try {
    jukebox.addSongToQueue(song1);
    jukebox.addSongToQueue(song1); // Throws IllegalArgumentException
} catch (IllegalArgumentException e) {
    System.out.println("Duplicate song detected!");
}
```

### Album Ordering
```java
// Songs in album are stored in LIFO order
Album album = new Album("Test Album");
album.addSong(new Song("First", "Artist"));  // Goes to bottom
album.addSong(new Song("Second", "Artist")); // Goes to middle
album.addSong(new Song("Third", "Artist"));  // Goes to top

// firstSong() returns most recently added
Song mostRecent = album.firstSong(); // Returns "Third"
```

## 🛠️ Development

### Testing Framework

#### Data Structure Tests
```java
public static boolean testStackAdd() {
    LinkedStack<Integer> stack = new LinkedStack<>();
    stack.push(1);
    stack.push(2);
    return !stack.isEmpty() && stack.peek() == 2 && stack.contains(1);
}

public static boolean testQueueAdd() {
    LinkedQueue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    return queue.size() == 2 && queue.peek() == 1; // FIFO order
}
```

#### Application-Level Tests
```java
public static boolean testAddSongToAlbum() {
    Album album = new Album("Test Album");
    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");
    
    album.addSong(song1);
    album.addSong(song2);
    
    // Check LIFO order - song2 should be first (most recent)
    return album.size() == 2 && album.firstSong().equals(song2);
}
```

### Adding New Features

#### Enhanced Song Metadata
```java
public class ExtendedSong extends Song {
    private int duration;           // Song length in seconds
    private String genre;           // Music genre
    private int year;              // Release year
    private double rating;         // User rating (1-5)
    
    public ExtendedSong(String name, String artist, int duration, String genre) {
        super(name, artist);
        this.duration = duration;
        this.genre = genre;
    }
    
    public int getDuration() { return duration; }
    public String getGenre() { return genre; }
}
```

#### Playlist Management
```java
public class Playlist {
    private String name;
    private LinkedQueue<Song> songs;
    private boolean repeatMode;
    private boolean shuffleMode;
    
    public void addSong(Song song) {
        songs.enqueue(song);
    }
    
    public Song nextSong() {
        Song next = songs.dequeue();
        if (repeatMode) {
            songs.enqueue(next); // Add back to end for repeat
        }
        return next;
    }
}
```

#### Advanced JukeBox Features
```java
public class AdvancedJukeBox extends JukeBox {
    private int coinsInserted;
    private int songsPerCoin;
    private LinkedStack<Song> history; // Recently played songs
    
    public boolean insertCoin() {
        coinsInserted++;
        return true;
    }
    
    public Song playSongIfPaid() {
        if (coinsInserted >= songsPerCoin) {
            coinsInserted -= songsPerCoin;
            Song played = playSong();
            history.push(played);
            return played;
        }
        throw new IllegalStateException("Insert more coins!");
    }
}
```

## 📈 Performance Characteristics

### Time Complexity

#### Stack Operations
- **Push**: O(1) - Add to front of linked list
- **Pop**: O(1) - Remove from front of linked list  
- **Peek**: O(1) - Access front node
- **Contains**: O(n) - Linear search through list

#### Queue Operations
- **Enqueue**: O(1) - Add to back of linked list
- **Dequeue**: O(1) - Remove from front of linked list
- **Peek**: O(1) - Access front node
- **Contains**: O(n) - Linear search through list

#### Application Operations
- **Add Song to Album**: O(1) - Stack push operation
- **Remove Song from Album**: O(1) - Stack pop operation
- **Add Song to JukeBox**: O(n) - Due to duplicate checking
- **Play Song**: O(1) - Queue dequeue operation
- **Shuffle Queue**: O(n) - ArrayList shuffle + rebuild queue

### Space Complexity
- **Per Song**: O(1) - Fixed metadata storage
- **Per Album**: O(n) - Where n is number of songs
- **Per JukeBox**: O(c) - Where c is capacity
- **Overall System**: O(total songs across all structures)

## 🎯 Design Patterns

### Template Method Pattern
```java
// Abstract data type interfaces define common operations
public interface StackADT<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();
    boolean contains(T value);
}

public interface QueueADT<T> {
    void enqueue(T value);
    T dequeue(); 
    T peek();
    boolean isEmpty();
    int size();
    void clear();
    boolean contains(T value);
}
```

### Adapter Pattern
```java
// Converting between different data structure formats
public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = front; // or top for stack
    
    while (current != null) {
        list.add(current.getData());
        current = current.getNext();
    }
    
    return list;
}
```

## 🐛 Troubleshooting

### Common Issues

1. **NullPointerException in contains()**
   ```java
   // Check for null before calling equals()
   public boolean contains(T value) {
       LinkedNode<T> current = top;
       while (current != null) {
           if (current.getData() != null && current.getData().equals(value)) {
               return true;
           }
           current = current.getNext();
       }
       return false;
   }
   ```

2. **Memory Leaks in Remove Operations**
   ```java
   // Properly clear references when removing nodes
   public T pop() {
       if (isEmpty()) return null;
       LinkedNode<T> toPop = top;
       top = top.getNext();
       toPop.setNext(null); // Clear reference to prevent memory leak
       return toPop.getData();
   }
   ```

3. **Queue State Inconsistency**
   ```java
   // Always maintain front/back pointer consistency
   public T dequeue() {
       if (isEmpty()) return null;
       LinkedNode<T> dequeue = front;
       
       if (size == 1) {
           front = back = null; // Reset both pointers for empty queue
       } else {
           front = front.getNext();
       }
       
       size--;
       return dequeue.getData();
   }
   ```

### Debug Techniques

#### Visualizing Data Structures
```java
public void debugPrintStack(LinkedStack<Song> stack) {
    System.out.println("Stack contents (top to bottom):");
    ArrayList<Song> list = stack.getList();
    for (int i = 0; i < list.size(); i++) {
        System.out.println("  " + i + ": " + list.get(i));
    }
}

public void debugPrintQueue(LinkedQueue<Song> queue) {
    System.out.println("Queue contents (front to back):");
    ArrayList<Song> list = queue.getList();
    for (int i = 0; i < list.size(); i++) {
        System.out.println("  " + i + ": " + list.get(i));
    }
}
```

#### State Validation
```java
public void validateJukeBoxState(JukeBox jukebox) {
    System.out.println("JukeBox State:");
    System.out.println("  Size: " + jukebox.size());
    System.out.println("  Capacity: " + jukebox.capacity());
    System.out.println("  Is Full: " + jukebox.isFull());
    System.out.println("  Is Empty: " + jukebox.isEmpty());
    System.out.println("  Contents: " + jukebox.toString());
}
```

## 🔮 Future Enhancements

### Planned Features
- [ ] Genre-based filtering and sorting
- [ ] User rating and recommendation system
- [ ] Playlist import/export functionality
- [ ] Real-time streaming integration
- [ ] Social sharing capabilities
- [ ] Voice command interface

### Advanced Features
- [ ] Machine learning for music recommendations
- [ ] Multi-user support with separate queues
- [ ] Cloud synchronization
- [ ] Mobile app integration
- [ ] Visualization of music data
- [ ] Audio analysis and smart shuffle

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Add your improvements
4. Run all tests to ensure functionality
5. Submit a pull request

### Contribution Guidelines
- Follow Java coding standards
- Maintain LIFO/FIFO ordering principles
- Add comprehensive tests for new features
- Document all public methods
- Test edge cases thoroughly

## 🆘 Support

If you encounter issues:

1. Run the test suite to verify system integrity
2. Check data structure ordering (LIFO for albums, FIFO for jukebox)
3. Verify null pointer handling
4. Test with edge cases (empty structures, single elements)
5. Open an issue with detailed error information

---

**Let the music play!** 🎶🎸

*Built with ❤️ by Rishabh Aggarwal*

### Academic Integrity Notice
This project was created for educational purposes as part of CS 300 coursework. Please respect academic integrity policies when using this code for learning or reference.
