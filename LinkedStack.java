
import java.util.ArrayList;

/**
 * A stack implementation using a linked list structure.
 *
 * @param <T> the type of elements stored in the stack
 * @author rishabhaggarwal
 */
public class LinkedStack<T> implements StackADT<T> {

  private LinkedNode<T> top; // Reference to the top node of the stack

  /**
   * Adds a new element to the top of the stack.
   *
   * @param value the element to be added to the stack
   */
  @Override
  public void push(T value) {

    LinkedNode<T> newNode = new LinkedNode<T>(value);

    // Link new node to current top and update top pointer

    if (top != null) {
      newNode.setNext(top);
    }

    top = newNode;

  }

  /**
   * Removes and returns the element at the top of the stack.
   *
   * @return the most recently added element, or null if stack is empty
   */
  @Override
  public T pop() {

    if (this.isEmpty()) {
      return null;
    }

    LinkedNode<T> toPop = top;

    // Handle case of single element

    if (top.getNext() == null) {
      top = null;
    } else {
      // Update top to next node
      top = top.getNext();
    }

    return toPop.getData();

  }

  /**
   * Returns the element at the top of the stack without removing it.
   *
   * @return the most recently added element, or null if stack is empty
   */
  @Override
  public T peek() {

    if (this.isEmpty()) {
      return null;
    }

    return top.getData();

  }

  /**
   * Checks if the stack is empty.
   *
   * @return true if stack contains no elements, false otherwise
   */
  @Override
  public boolean isEmpty() {

    return top == null;

  }

  /**
   * Checks if a specific element exists in the stack.
   *
   * @param value the element to search for
   * @return true if element is found in stack, false otherwise
   */
  @Override
  public boolean contains(T value) {

    if (this.isEmpty()) {
      return false;
    }

    // Traverse stack checking each node

    LinkedNode<T> current = top;

    while (current != null) {

      if (current.getData().equals(value)) {
        return true;
      }

      current = current.getNext();

    }

    return false;

  }

  /**
   * Creates an ArrayList containing all elements in the stack. Elements are ordered from top to
   * bottom.
   *
   * @return ArrayList containing stack elements in LIFO order
   */
  public ArrayList<T> getList() {

    ArrayList<T> list = new ArrayList<T>();

    if (this.isEmpty()) {
      return list;
    }

    // Copy all elements to ArrayList maintaining order

    LinkedNode<T> current = top;

    while (current != null) {

      list.add(current.getData());
      current = current.getNext();

    }

    return list;

  }

}
