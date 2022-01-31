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

        // call addLast since default behavior will be to add elements to the end of the list; handles sz++
        addLast(element);

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

        //create a container to hold the helper functions returned node
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);
        
        //creating this new node and pointing to the adjacent nodes
        MyNode<T> myNode = new MyNode<T>(element, indexedNode, indexedNode.prev);

        //the previous node will point to this new node
        indexedNode.prev.next = myNode;

        //the indexed node will point back to the new node
        indexedNode.prev = myNode;

        sz++;
    }

    // Not from either interface
    public void addFirst(T element) {
        //creating this new node and pointing it to the adjacent nodes
        MyNode<T> myNode = new MyNode<T>(element, dummyHead.next, dummyHead);

        //point the prev first node back to this new node, new node points to the old first nod eand back to head
        dummyHead.next.prev = myNode;

        //change the first node by pointing to the new node using the reference we just set
        dummyHead.next = myNode;

        sz++;
    }

    // Not from either interface
    public void addLast(T element) {
        //creating this new node and pointing it to the adjacent nodes
        MyNode<T> myNode = new MyNode<T>(element,dummyTail,dummyTail.prev);

        //point the old prev to the new node
        dummyTail.prev.next = myNode;

        //point dummytail back to the new node
        dummyTail.prev = myNode;

        sz++;
    }

    public void clear() {
        //point these back to the variables to donate everything to the garbage collecter and reset
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;

        //reset the size
        sz = 0;
        }

    public boolean contains(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //variable for if the element is here in our list
        boolean here = false;

        //callindexof and it's -1 then that means it wasn't in the list
        if(indexOf(element) !=-1 )
            here = true;

        return here;
    }

    // Comes from MyQueue interface
    public T element() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        //return the value of whatever dummyHead points to
        return dummyHead.next.val;
    }

    public T get(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        T ourValue;

        //check where to start and then return with the cheaper method
        ourValue = (index < (sz/2)) ? iterateFromFront(index).val : iterateFromRear(index).val;
        
        return ourValue;

    }

    // Not from either interface
    public T getFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        //return the value of whatever dummyHead points to
        return dummyHead.next.val;
    }

    // Not from either interface
    public T getLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        //return the value of whatever dummytail points to
        return dummyTail.prev.val;
    }

    public int indexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a container for our nodes to loop through
        MyNode<T> iteratorNode = dummyHead.next;
        //loop through the list and check if element is here
        int here = -1;
        for(int i =0; i < sz; i++){
            if(iteratorNode.val ==null ? element==null : iteratorNode.val.equals(element)){
                here = i;
                break;
            }
            iteratorNode = iteratorNode.next;
        }
        return here;
    }

    public boolean isEmpty() {
       //if size hasn't been incremented then the list is empty
       return (sz == 0);
    }

    public int lastIndexOf(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

       //create a container for our nodes to loop through
       MyNode<T> iteratorNode = dummyTail.prev;
       //loop through the list and check if element is here
       int here = -1;
       //start from the back and break when it's found
       for(int i = sz-1; i >= 0; i--){
           if(iteratorNode.val ==null ? element==null : iteratorNode.val.equals(element)){
               here = i;
               break;
           }
           iteratorNode = iteratorNode.prev;
       }
       return here;
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

        // call addLast since default behavior will be to add elements to the end of the list; handles sz++
        addLast(element); 

        return true; // No capacity in our implementation
    }

    // Comes from MyQueue interface
    public T peek() {
        if (sz == 0) {
            return null;
        }

        //peek at what is in the front of the queue
        return dummyHead.next.val;
    }

    // Comes from MyQueue interface
    public T poll() {
        if (sz == 0) {
            return null;
        }

        //remove the head of this queue and store it for later use
        T removedValue = removeFirst();
        
        //later use
        return removedValue;
    }

    // Comes from MyQueue interface
    public T remove() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }
        //remove the head of this queue and store it for later use
        T removedValue = removeFirst();
        
        //later use
        return removedValue;
    }

    public T remove(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }

        //create a new index node and iterate with the cheapest option
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);


        T ret = indexedNode.val;
        indexedNode.prev.next = indexedNode.next;
        indexedNode.next.prev = indexedNode.prev;

        sz--;
        return ret;
    }

    public boolean remove(T element) {
        if (!first && element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }
       //create a container for our nodes to loop through
       MyNode<T> iteratorNode = dummyHead.next;
       //loop through the list and check if element is here
       boolean here = false;
       //start from the back and break when it's found
       for(int i = sz-1; i >= 0; i--){
           if(iteratorNode.val ==null ? element==null : iteratorNode.val.equals(element)){
               here = true;
               break;
           }
           iteratorNode = iteratorNode.next;
       }
   
       iteratorNode.prev.next = iteratorNode.next;
       iteratorNode.next.prev = iteratorNode.prev;        
       
       sz--;
       return here;
    }

    // Not from either interface
    public T removeFirst() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = dummyHead.next.val;
        //change the pointers to exlcude dummyHead.next using the .next.ext node
        dummyHead.next = dummyHead.next.next;
        dummyHead.next.prev = dummyHead;

        sz--;

        return ret;
    }

    // Not from either interface
    public T removeLast() {
        if (sz == 0) {
            throw new NoSuchElementException();
        }

        T ret = dummyTail.prev.val;
        //change the pointers to exclude prev and let the garbage collecter handle the rest
        dummyTail.prev = dummyTail.prev.prev;
        dummyTail.prev.next = dummyTail;

        sz--;
    
        return ret;
    }

    public T set(int index, T element) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        if (element.getClass() != dataType) {
            throw new InputMismatchException("Type mismatch");
        }

        //create a new index node and iterate with the cheapest option
        MyNode<T> indexedNode;
        indexedNode = index < (sz/2) ? iterateFromFront(index): iterateFromRear(index);

        T ret = indexedNode.val;
        indexedNode.val = element;

        return ret;
    }

    public int size() {
        // return the size of the linkedlist
        return sz;
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
