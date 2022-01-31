/**
 * Author: Mike Sauce
 */
public interface MyList<T> {
    public boolean add(T element);

    public void add(int index, T element);

    public void clear();

    public boolean contains(T element);

    public T get(int index);

    public int indexOf(T element);

    public boolean isEmpty();

    public int lastIndexOf(T element);

    public T remove(int index);

    public boolean remove(T element);

    public T set(int index, T element);

    public int size();
}
