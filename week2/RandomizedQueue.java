import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] elements;
    private int size, first, last;


    // construct an empty randomized queue
    public RandomizedQueue() {
        //elements = (Item[]) new Object[1];
        elements = (Item[]) new Object[1];
        size = 0;
        first = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item can not be null");
        }
        if (last == elements.length) resize(2 * elements.length);
        elements[last++] = item;
        size += 1;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = elements[i];
        elements = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Randommized Queue is empty");
        int randIndex = StdRandom.uniform(first, last);
        Item tmp = elements[randIndex];
        elements[randIndex] = elements[--last];
        elements[last] = null;
        size -= 1;
        if (isEmpty()) {
            first = 0;
            last = 0;
        }
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return tmp;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Randommized Queue is empty");
        int randIndex = StdRandom.uniform(first, last);
        return elements[randIndex];

    }

    private class RandomIterator implements Iterator<Item> {
        private int count;
        private Item[] elems;
        private int num = 0;

        private RandomIterator() {
            count = size;
            elems = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                elems[i] = elements[i + first];
            }
        }

        public boolean hasNext() {
            return count != 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove method is not suppoted");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int randIndex = StdRandom.uniform(count);
            Item tmp = elems[randIndex];
            elems[randIndex] = elems[--count];
            elems[count] = tmp;
            return tmp;


        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();

    }

    // unit testing (required)
    public static void main(String[] args) {
        
        RandomizedQueue<String> que = new RandomizedQueue<String>();
        que.enqueue("to");
        que.enqueue("be");
        que.enqueue("or");
        que.dequeue();
        que.dequeue();
        que.dequeue();
        que.enqueue("not");
        que.dequeue();
        que.enqueue("to");
        que.dequeue();
        que.enqueue("be");
        que.enqueue("that");
        que.enqueue("is");
        que.enqueue("the");
        que.dequeue();
        que.dequeue();
        que.enqueue("question");
        que.dequeue();
        que.enqueue("or");
        que.enqueue("not");


        for (String s : que)
            StdOut.print(s + " ");
        StdOut.println();
        StdOut.println("random sample: " + que.sample());       


    }

}
