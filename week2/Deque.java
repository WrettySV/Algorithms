import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int size;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;

    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item can not be null");
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = null;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item can not be null");
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.previous = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
            last.previous = oldlast;
        }
        size += 1;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque is empty");
        Item item = first.item;
        first = first.next;
        size -= 1;
        if (isEmpty()) last = null;
        else first.previous = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque is empty");
        Item item = last.item;
        last = last.previous;
        size -= 1;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<Items> implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove method is not suppoted");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void printIterable(Deque<String> deque) {
        for (String str : deque) System.out.print(str + " ");
        System.out.println();

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        Iterator<String> i = deque.iterator();
        deque.addFirst("to");
        deque.addFirst("be");
        deque.addLast("that");
        deque.addLast("why");
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.addLast("that");
        deque.addFirst("to");
        deque.addLast("is");
        deque.addLast("the");
        deque.addLast("question");
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.addFirst("or");
        deque.removeFirst();
        deque.addLast("or");
        deque.addFirst("not");
        for (String s : deque) {
            StdOut.println(s);
        }


    }

}
