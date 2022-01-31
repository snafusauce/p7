/**
 * Author: Mike Sauce
 */
public interface MyQueue<T> {
    public boolean add(T element);

    public T element();

    public boolean offer(T element);

    public T peek();

    public T poll();

    public T remove();
}
