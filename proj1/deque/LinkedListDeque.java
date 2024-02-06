package deque;

public class LinkedListDeque<T> {
    /** The basic node for the Deque. */
    private class StuffNode {

        T value;
        StuffNode next;
        StuffNode prev;
        StuffNode(T v, StuffNode n, StuffNode p)  {
            value = v;
            next = n;
            prev = p;
        }

    }
    public static void main(String[] args) {

    }

    private int size = 0;
    StuffNode sentinel = new StuffNode(null,null,null);

    /** This implementation depends on circular sentinel node topology that means than this sentinel node refer to the first and the end of the deque. */
    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Add an item x to the first of the deque. */
    public void addFirst(T x) {
        StuffNode newNode = new StuffNode(x, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    /** Add an item x to the end of the deque. */
    public void addLast(T x) {
        StuffNode newNode = new StuffNode(x,sentinel,sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /** Remove the first element of the deque. */
    public T removeFirst() {
        if(isEmpty()) return null;
        StuffNode newFirst = sentinel.next.next;
        sentinel.next = newFirst;
        newFirst.prev.next = null;
        newFirst.prev = sentinel;
        return newFirst.value;
    }

    /** Remove the last element of the deque. */
    public T removeLast() {
        if(isEmpty()) return null;
        StuffNode newEnd = sentinel.prev.prev;
        newEnd.next.prev = null;
        newEnd.next = sentinel;
        sentinel.prev = newEnd;
        return newEnd.value;
    }

    /** Check if the deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the size of the deque. */
    public int size() {
        return size;
    }

    /** Returns the index-th element using an iteration. */
    public T get(int index) {
        if(index < 0 || index >= size) return null;
        StuffNode p = sentinel;
        index++;
        while(index-- > 0) {
            p = p.next;
        }
        return p.value;
    }

    /** Helper method to get the element using recursion */
    private T getRecursiveHelper(int i,StuffNode p) {
        if(i == 0) return p.value;

        return getRecursiveHelper(i-1,p.next);
    }

    /** get the index-th item using recursion. */
    public T getRecursive(int index) {
        if(index < 0 || index >= size) return null;
        return getRecursiveHelper(index+1,sentinel);
    }

    /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
        StuffNode p = sentinel;
        while(p.next != sentinel) {
            System.out.print(p.next.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**  Returns whether the parameter o is equal to the Deque or not. o is considered equal if it is a Deque and if it contains the same contents. */
    public boolean equals(Object o) {
        if(!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> otherDeq = (LinkedListDeque<T>) o;
        StuffNode p = sentinel;
        StuffNode otherP = otherDeq.sentinel;
        if(this.size() != otherDeq.size()) {
            return false;
        }
        while (p.next != sentinel) {
            if(!(p.next.value.equals(otherP.next.value))) {
                return false;
            }
            p = p.next;
            otherP = otherP.next;
        }
        return true;
    }


}
