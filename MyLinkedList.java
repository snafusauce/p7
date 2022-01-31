/**
 * Author: Mike Sauce
 */
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T>, MyQueue<T> {
    private Class dataType; // The type of data we have in our Linked List
    private boolean first;  // True until Linked List adds its first element
    private MyNode<T> dummyHead;
    private MyNode<T> dummyTail;
    private int sz;

    // No-argument constructor
    public MyLinkedList() {
        first = true;
        sz = 0;
        dummyHead = new MyNode<>();
        dummyTail = new MyNode<>();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    // Comes from both interfaces
    public boolean add(T element) {
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return true; // (as specified by Collection.add(E))
    }

    public void add(int index, T element) {
        if (index < 0 || sz < index) {
            throw new IndexOutOfBoundsException();
        }
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
    }

    // Not from either interface
    public void addFirst(T element) {
        // ...
    }

    // Not from either interface
    public void addLast(T element) {
        // ...
    }

    public void clear() {
        // ...
    }

    public boolean contains(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return false;
    }

    // Comes from MyQueue interface
    public T element() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        // ...
        return null;
    }

    public T get(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        // ...

        return null;
    }

    // Not from either interface
    public T getFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        // ...

        return null;
    }

    // Not from either interface
    public T getLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        // ...

        return null;
    }

    public int indexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return -1;
    }

    public boolean isEmpty() {
        // ...
        return true;
    }

    public int lastIndexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return -1;
    }

    // Comes from MyQueue interface
    public boolean offer(T element) {
        if (first) {
            dataType = element.getClass();
            first = false;
        }
        if (element.getClass() != dataType) {
            return false;
        }

        // ...
        return false; // No capacity in our implementation
    }

    // Comes from MyQueue interface
    public T peek() {
        if (sz == 0) {
            return null;
        }

        // ...

        return null;
    }

    // Comes from MyQueue interface
    public T poll() {
        if (sz == 0) {
            return null;
        }

        T ret = null;
        // ...

        return null;
    }

    // Comes from MyQueue interface
    public T remove() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    public T remove(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        // ...

        T ret = null;
        // ...

        return null;
    }

    public boolean remove(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...
        return false;
    }

    // Not from either interface
    public T removeFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    // Not from either interface
    public T removeLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = null;
        // ...

        return null;
    }

    public T set(int index, T element) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        // ...

        T ret = null;
        // ...

        return null;
    }

    public int size() {
        // ...
        return 0;
    }

    // Helper functions
    private MyNode<T> iterateFromFront(int index) {
        MyNode<T> iteratorNode = dummyHead.next;
        int i = 0;
        while (i != index) {
            iteratorNode = iteratorNode.next;
            ++i;
        }

        return iteratorNode;
    }

    private MyNode<T> iterateFromRear(int index) {
        MyNode<T> iteratorNode = dummyTail.prev;
        int i = sz - 1;
        while (i != index) {
            iteratorNode = iteratorNode.prev;
            --i;
        }

        return iteratorNode;
    }
}

/**
 * Usage in main():
 * MyLinkedList<Integer> myFirstList =
 *     new MyLinkedList<Integer>();
 * myFirstList.add(5);
 * ...
 *
 * MyLinkedList<String> mySecondList =
 *     new MyLinkedList<String>();
 * mySecondList.add("Hello World");
 * ...
 */
