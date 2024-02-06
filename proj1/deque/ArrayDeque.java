package deque;
public class ArrayDeque<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int front;
    private int back;
    private int st;
    private final int MINIMUM_CAPACITY = 8;

    ArrayDeque() {
        size = 0;
        front = 4;
        back = 4;
        st  = 4;
    }
    /** Resize the array to the new capacity. */
    private void resize(int capacity) {
        T[] newArray = (T[])new Object[capacity];
        int i = 0;
        while(front != back) {
            newArray[i++] = items[front];
            front = nextFront(front);
        }
        front = 0;
        back = i;
        items = newArray;


    }
    /** Helper method to get the next front int the deque. */
    private int nextFront(int front) {
        return (front-1+(items.length)) % items.length;
    }
    /** Helper method to get the next back int the deque */
    private int nextBack(int back) {
        return (back+1) % items.length;
    }

    /** Helper method to get the previous front in the deque. */
    private int prevFront() {
        return (front+1) % items.length;
    }
    /** Helper method to get the previous back in the deque. */
    private int prevBack() {
        return (back - 1 + items.length) % items.length;
    }
    /** Add item to the first of the deque. */
    public void addFirst(T item) {
        if(items[nextFront(front)] != null) {
            resize(2* items.length);
        }
        items[nextFront(front)] = item;
        front = nextFront(front);
        size++;
    }
    /** Add item to the last of the deque. */
    public void addLast(T item) {
        if(items[nextBack(back)] != null) {
            resize(2* items.length);
        }
        items[nextBack(back)] = item;
        back = nextBack(back);
        size++;
    }

    /** check if the deque is empty. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Remove the last element in the deque. */
    public void removeLast() {
        items[back] = null;
        back = prevBack();
        size--;
        if(size < items.length/4 && items.length >= MINIMUM_CAPACITY*2) {
            resize(items.length/2);
        }
    }

    /** Remove the first item in the deque. */
    public void removeFirst() {
        items[front] = null;
        front = prevFront();
        size--;
        if(size < items.length/4 && items.length >= MINIMUM_CAPACITY*2) {
            resize(items.length/2);
        }
    }

    public T get(int idx) {
        return items[(front+idx)% items.length];
    }

    public void printDeque() {
        int currFirst = front;
        int currRear = back;
        while (currFirst != currRear) {
            System.out.print(items[currFirst] + " ");
            currFirst  = nextFront(currFirst);
        }
        System.out.println();
    }

}
