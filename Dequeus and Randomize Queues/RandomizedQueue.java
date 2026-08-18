/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        count = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return count;

    }

    // Resize our array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < count; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null to the array!");
        }


        if (count == queue.length) {
            resize(2 * queue.length);
        }

        queue[count++] = item;

    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        int randomInt = StdRandom.uniformInt(count);
        Item item = queue[randomInt];
        queue[randomInt] = queue[count - 1];
        queue[count - 1] = null;
        count--;
        if (count > 0 && count == queue.length / 4) resize(queue.length / 2);
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        return queue[StdRandom.uniformInt(count)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();

    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy;
        private int i;

        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[count];
            for (int j = 0; j < count; j++) {
                if (queue[j] != null) copy[j] = queue[j];
            }
            StdRandom.shuffle(copy);
            i = count;
        }


        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            return copy[--i];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        StdOut.println(rq.isEmpty());
        StdOut.println("Queue Size: " + rq.size());
        StdOut.println("Sample: " + rq.sample());

        for (String s : rq) {
            StdOut.println(s);
        }

        StdOut.println("Removed: " + rq.dequeue());

        for (String s : rq) {
            StdOut.println(s);
        }


        Iterator<String> it = rq.iterator();

        StdOut.println("Next item in queue: " + it.next());


        while (it.hasNext()) {
            it.remove();
        }

    }

}
