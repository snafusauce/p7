/**
 * Author: Mike Sauce
 */
public class MyNode<T> {
    public T val;
    public MyNode<T> next;
    public MyNode<T> prev;

    // No-argument constructor
    public MyNode() {
        this.val = null;
        next = null;
        prev = null;
    }

    // One-argument constructor
    public MyNode(T val) {
        this.val = val;
        next = null;
        prev = null;
    }

    // Two-argument constructor
    public MyNode(T val, MyNode<T> next) {
        this.val = val;
        this.next = next;
        prev = null;
    }

    // Three-argument constructor
    public MyNode(T val, MyNode<T> next, MyNode<T> prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
