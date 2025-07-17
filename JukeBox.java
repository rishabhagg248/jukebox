//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: JukeBox
// Course: CS 300 Fall 2024
//
// Author: Swati Banwani
// Email: banwani@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Represents a jukebox that manages a queue of songs with a fixed capacity. Uses LinkedQueue to
 * maintain FIFO ordering of songs for playback.
 * 
 * @author swatibanwani
 */
public class JukeBox {

  private int capacity; // Maximum number of songs allowed

  private LinkedQueue<Song> songQueue; // Queue to store songs for playback

  /**
   * Creates a new JukeBox with specified capacity.
   *
   * @param capacity maximum number of songs allowed
   * @throws IllegalArgumentException if capacity is negative
   */
  public JukeBox(int capacity) {

    if (capacity < 0) {
      throw new IllegalArgumentException();
    }

    this.capacity = capacity;
    songQueue = new LinkedQueue<Song>();

  }

  /**
   * Adds songs from an album to the queue until full or album is empty. Maintains the order of
   * songs using a temporary stack.
   *
   * @param album the album containing songs to add
   */
  public void addAlbumToQueue(Album album) {

    // Temporary stack to reverse order of songs

    LinkedStack<Song> tempStack = new LinkedStack<>();

    // Extract songs from album while space available

    while (!isFull()) {

      try {

        Song song = album.removeSong();
        tempStack.push(song);

      } catch (NoSuchElementException e) {
        break;
      }

    }

    // Add songs to queue in correct order
    while (!tempStack.isEmpty()) {

      try {

        addSongToQueue(tempStack.pop());

      } catch (IllegalStateException | IllegalArgumentException e) {
        continue;
      }

    }

  }

  /**
   * Adds a single song to the queue if space allows and song isn't duplicate.
   *
   * @param song the song to add
   * @throws IllegalStateException    if queue is full
   * @throws IllegalArgumentException if song already in queue
   */
  public void addSongToQueue(Song song) {

    if (isFull()) {
      throw new IllegalStateException();
    }

    // Check for duplicate songs

    ArrayList<Song> songs = songQueue.getList();

    for (Song s : songs) {

      if (s.equals(song)) {
        throw new IllegalArgumentException();
      }

    }

    songQueue.enqueue(song);

  }

  /**
   * Returns the maximum capacity of the jukebox.
   *
   * @return maximum number of songs allowed
   */
  public int capacity() {

    return this.capacity;

  }

  /**
   * Checks if queue is empty.
   *
   * @return true if no songs in queue
   */
  public boolean isEmpty() {

    return songQueue.isEmpty();

  }

  /**
   * Checks if queue is at capacity.
   *
   * @return true if number of songs equals capacity
   */
  public boolean isFull() {

    return songQueue.size() >= this.capacity;

  }

  /**
   * Removes and returns the next song in queue for playback.
   *
   * @return the next song to play
   * @throws NoSuchElementException if queue is empty
   */
  public Song playSong() {

    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    return songQueue.dequeue();

  }

  /**
   * Randomly reorders songs in the queue.
   */
  public void shuffleSongQueue() {

    // Get current songs and shuffle them

    ArrayList<Song> songs = songQueue.getList();

    Collections.shuffle(songs);

    // Rebuild queue with shuffled songs

    songQueue.clear();

    for (Song song : songs) {

      songQueue.enqueue(song);

    }

  }

  /**
   * Returns current number of songs in queue.
   *
   * @return number of songs in queue
   */
  public int size() {

    return songQueue.size();

  }

  /**
   * Returns string representation of queue. Format: Song1 -> Song2 -> ... -> END
   *
   * @return formatted string of queue contents
   */
  public String toString() {

    ArrayList<Song> list = songQueue.getList();

    if (list.isEmpty()) {
      return "END";
    }

    // Build string with arrows between songs

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < list.size(); i++) {

      sb.append(list.get(i).toString());
      sb.append(" -> ");

    }

    sb.append("END");

    return sb.toString();

  }
}
