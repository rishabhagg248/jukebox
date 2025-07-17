
import java.util.ArrayList;

/**
 * A generic queue implementation using a linked list structure. Implements the QueueADT interface
 * to provide FIFO (First-In-First-Out) functionality.
 *
 * @param <T> the type of elements stored in the queue
 * @author rishabhaggarwal
 */
public class LinkedQueue<T> implements QueueADT<T> {

  private LinkedNode<T> back; // Reference to the last node in queue

  private LinkedNode<T> front; // Reference to the first node in queue

  private int size; // Number of elements in queue

  /**
   * Adds a new element to the back of the queue.
   *
   * @param value the element to be added to the queue
   */
  @Override
  public void enqueue(T value) {

    LinkedNode<T> newNode = new LinkedNode<T>(value);

    // If queue is empty, set both front and back to new node

    if (this.isEmpty()) {

      front = newNode;
      back = newNode;

    } else {

      // Link new node at back of queue
      back.setNext(newNode);
      back = newNode;

    }

    size++;

  }

  /**
   * Removes and returns the element at the front of the queue.
   *
   * @return the first element in queue, or null if empty
   */
  @Override
  public T dequeue() {

    if (this.isEmpty()) {
      return null;
    }

    // Save front node to return its data

    LinkedNode<T> dequeue = front;

    // Handle case of single element

    if (size == 1) {

      this.clear();

    } else {

      // Move front pointer to next node
      front = front.getNext();

    }

    size--;

    return dequeue.getData();

  }

  /**
   * Returns the element at the front of the queue without removing it.
   *
   * @return the first element in queue, or null if empty
   */
  @Override
  public T peek() {

    if (this.isEmpty()) {
      return null;
    }

    return front.getData();

  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if queue contains no elements
   */
  @Override
  public boolean isEmpty() {

    return front == null && back == null && size == 0;

  }

  /**
   * Returns the number of elements in the queue.
   *
   * @return current size of queue
   */
  @Override
  public int size() {

    return this.size;

  }

  /**
   * Removes all elements from the queue.
   */
  @Override
  public void clear() {

    back = null;
    front = null;
    size = 0;

  }

  /**
   * Checks if a specific element exists in the queue.
   *
   * @param value element to search for
   * @return true if element is found
   */
  @Override
  public boolean contains(T value) {

    if (this.isEmpty()) {
      return false;
    }

    // Traverse queue checking each node

    LinkedNode<T> current = front;

    while (current != null) {

      if (current.getData().equals(value)) {
        return true;
      }

      if (current.equals(back)) {
        break;
      }

      current = current.getNext();

    }

    return false;

  }

  /**
   * Creates an ArrayList containing all elements in the queue. Elements are ordered from front to
   * back.
   *
   * @return ArrayList containing queue elements in FIFO order
   */
  public ArrayList<T> getList() {

    ArrayList<T> list = new ArrayList<T>();

    if (this.isEmpty()) {
      return list;
    }

    // Copy all elements to ArrayList maintaining order

    LinkedNode<T> current = front;

    while (current != null) {

      list.add(current.getData());

      if (current.equals(back)) {
        break;
      }

      current = current.getNext();

    }

    return list;

  }
}
