/* *****************************************************************************
 *  Name: Kareem Mohamed
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private int count;

    // construct an empty deque
    public Deque() {
        count = 0;

        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to Deque");
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        if (oldFirst == null) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }

        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to Deque");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;

        if (oldLast == null) {
            first = last;
        }
        else {
            oldLast.next = last;
        }

        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty!");
        }

        Item item = first.item;
        first = first.next;
        count--;

        if (isEmpty()) {
            last = null;
        }
        else {
            first.previous = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty!");
        }

        Item item = last.item;
        last = last.previous;
        count--;

        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> dq = new Deque<String>();

        dq.addLast("1");
        dq.addFirst("2");
        dq.addFirst("3");
        dq.addFirst("5");
        dq.addLast("4");
        StdOut.println(dq.isEmpty());
        StdOut.println("Deque Size: " + dq.size());

        for (String s : dq) {
            StdOut.println(s);
        }

        StdOut.println("Removed: " + dq.removeLast());

        Iterator<String> it = dq.iterator();

        System.out.println("Next item: " + it.next());

        StdOut.println("Removed: " + dq.removeFirst());
        StdOut.println("Size: " + dq.removeFirst());

        for (String s : dq) {
            StdOut.println(s);
        }

        while (it.hasNext()) {
            it.remove();
        }


    }

}
