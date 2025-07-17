
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {

    LinkedStack<Integer> stack = new LinkedStack<>();

    stack.push(1);
    stack.push(2);

    return !stack.isEmpty() && stack.peek() == 2 && stack.contains(1);

  }

  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {

    LinkedStack<Integer> stack = new LinkedStack<>();

    stack.push(1);
    stack.push(2);

    Integer popped = stack.pop();

    return popped == 2 && stack.peek() == 1 && !stack.contains(2);

  }

  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {

    LinkedQueue<Integer> queue = new LinkedQueue<>();

    queue.enqueue(1);
    queue.enqueue(2);

    // Test FIFO order
    ArrayList<Integer> list = queue.getList();
    if (list.get(0) != 1 || list.get(1) != 2)
      return false;

    // Test size and first element
    return queue.size() == 2 && queue.peek() == 1;

  }

  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {

    LinkedQueue<Integer> queue = new LinkedQueue<>();

    queue.enqueue(1);
    queue.enqueue(2);

    Integer dequeued = queue.dequeue();

    return dequeued == 1 && queue.peek() == 2 && !queue.contains(1);

  }

  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {

    LinkedStack<Integer> stack = new LinkedStack<>();
    LinkedQueue<Integer> queue = new LinkedQueue<>();

    // Test empty state
    if (stack.peek() != null || queue.peek() != null)
      return false;

    stack.push(1);
    stack.push(2);
    queue.enqueue(1);
    queue.enqueue(2);

    // Save initial peek values
    Integer stackPeek = stack.peek();
    Integer queuePeek = queue.peek();

    // Verify peek doesn't modify structure
    if (stack.peek() != stackPeek || queue.peek() != queuePeek)
      return false;

    // Verify peek returns correct values and structure is maintained
    ArrayList<Integer> stackList = stack.getList();
    ArrayList<Integer> queueList = queue.getList();

    return stackPeek == 2 && queuePeek == 1 && stackList.size() == 2 && queueList.size() == 2
        && stackList.get(0) == 2 && queueList.get(0) == 1;
  }

  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {

    LinkedStack<Integer> stack = new LinkedStack<>();
    LinkedQueue<Integer> queue = new LinkedQueue<>();

    stack.push(1);
    stack.push(2);
    queue.enqueue(1);
    queue.enqueue(2);

    // Test presence of all elements
    if (!stack.contains(1) || !stack.contains(2) || !queue.contains(1) || !queue.contains(2))
      return false;

    // Test non-existent elements
    if (stack.contains(3) || queue.contains(3))
      return false;

    // Remove elements and verify contains
    stack.pop();
    queue.dequeue();

    return !stack.contains(2) && !queue.contains(1);

  }

  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {

    LinkedStack<Integer> stack = new LinkedStack<>();
    LinkedQueue<Integer> queue = new LinkedQueue<>();

    stack.push(1);
    stack.push(2);

    queue.enqueue(1);
    queue.enqueue(2);

    ArrayList<Integer> stackList = stack.getList();
    ArrayList<Integer> queueList = queue.getList();

    return stackList.get(0) == 2 && stackList.get(1) == 1 && queueList.get(0) == 1
        && queueList.get(1) == 2;

  }

  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {

    Album album = new Album("Test Album");

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");

    album.addSong(song1);

    // Test duplicate addition
    try {
      album.addSong(song1);
      return false; // Should have thrown exception
    } catch (IllegalArgumentException e) {
      // Expected behavior
    }

    album.addSong(song2);

    return album.size() == 2 && album.firstSong().equals(song2);

  }

  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {

    Album album = new Album("Test Album");
    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");

    // Test removing from empty album
    try {
      album.removeSong();
      return false; // Should have thrown exception
    } catch (NoSuchElementException e) {
      // Expected behavior
    }

    album.addSong(song1);
    album.addSong(song2);

    Song removed = album.removeSong();
    return album.size() == 1 && removed.equals(song2) && album.firstSong().equals(song1);
  }

  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {

    Album album = new Album("Test Album");

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");

    album.addSong(song1);
    album.addSong(song2);

    String expected = "Test Album\n" + song2.toString() + "\n" + song1.toString();

    return album.toString().equals(expected);

  }

  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {

    JukeBox jukebox = new JukeBox(2);

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");

    jukebox.addSongToQueue(song1);

    // Test duplicate addition
    try {
      jukebox.addSongToQueue(song1);
      return false; // Should have thrown exception
    } catch (IllegalArgumentException e) {
      // Expected behavior
    }

    jukebox.addSongToQueue(song2);

    return jukebox.size() == 2 && jukebox.isFull();

  }

  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {

    JukeBox jukebox = new JukeBox(2);

    Album album = new Album("Test Album");

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");
    Song song3 = new Song("Song3", "Artist3");

    album.addSong(song1);
    album.addSong(song2);
    album.addSong(song3);

    jukebox.addAlbumToQueue(album);

    return jukebox.size() == 2;

  }

  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {

    JukeBox jukebox = new JukeBox(2);

    // Test playing from empty jukebox
    try {
      jukebox.playSong();
      return false; // Should have thrown exception
    } catch (NoSuchElementException e) {
      // Expected behavior
    }

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");

    jukebox.addSongToQueue(song1);
    jukebox.addSongToQueue(song2);

    Song played = jukebox.playSong();

    return played.equals(song1) && jukebox.size() == 1;

  }

  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {

    JukeBox jukebox = new JukeBox(3);

    Song song1 = new Song("Song1", "Artist1");
    Song song2 = new Song("Song2", "Artist2");
    Song song3 = new Song("Song3", "Artist3");

    jukebox.addSongToQueue(song1);
    jukebox.addSongToQueue(song2);
    jukebox.addSongToQueue(song3);

    String beforeShuffle = jukebox.toString();
    jukebox.shuffleSongQueue();
    String afterShuffle = jukebox.toString();

    return !beforeShuffle.equals(afterShuffle) && jukebox.size() == 3;

  }

  public static void main(String[] args) {
    // Running and printing results for all the tests

    boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}
