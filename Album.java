import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a music album containing a collection of songs in LIFO order. Uses LinkedStack to
 * maintain the ordering of songs.
 * 
 * @author rishabhaggarwal
 */
public class Album {

  private String albumName; // Name of the album

  private int size; // Number of songs in the album

  private LinkedStack<Song> trackList; // Stack to store songs

  /**
   * Creates a new Album with the specified name.
   *
   * @param albumName the name of the album
   * @throws IllegalArgumentException if albumName is null or empty
   */
  public Album(String albumName) {

    if (albumName == null || albumName.isEmpty()) {
      throw new IllegalArgumentException();
    }

    // Initialize album with empty track list

    this.albumName = albumName;
    this.size = 0;
    this.trackList = new LinkedStack<Song>();

  }

  /**
   * Adds a song to the album if it doesn't already exist.
   *
   * @param s the song to add
   * @throws IllegalArgumentException if song already exists in album
   */
  public void addSong(Song s) {

    // Check for duplicate songs

    ArrayList<Song> songs = trackList.getList();

    for (Song song : songs) {

      if (song.equals(s)) {
        throw new IllegalArgumentException();
      }

    }

    // Add song and update album reference

    s.setAlbum(this);
    trackList.push(s);
    size++;

  }

  /**
   * Returns the most recently added song without removing it.
   *
   * @return the most recent song, or null if album is empty
   */
  public Song firstSong() {

    if (size == 0) {
      return null;
    }

    return trackList.peek();

  }

  /**
   * Returns the name of the album.
   *
   * @return album name
   */
  public String getAlbumName() {

    return this.albumName;

  }

  /**
   * Removes and returns the most recently added song.
   *
   * @return the removed song
   * @throws NoSuchElementException if album is empty
   */
  public Song removeSong() {

    if (size == 0) {
      throw new NoSuchElementException();
    }

    size--;

    return trackList.pop();

  }

  /**
   * Returns the number of songs in the album.
   *
   * @return number of songs
   */
  public int size() {

    return this.size;

  }

  /**
   * Returns string representation of album. Format: albumName\nsong1\nsong2\n...
   *
   * @return formatted string of album contents
   */
  public String toString() {

    if (size == 0) {
      return albumName;
    }

    // Build string with album name and songs

    StringBuilder sb = new StringBuilder();

    sb.append(albumName).append("\n");

    ArrayList<Song> list = trackList.getList();

    for (int i = 0; i < list.size(); i++) {

      sb.append(list.get(i).toString());

      if (i < list.size() - 1) {
        sb.append("\n");
      }

    }

    return sb.toString();

  }
}
