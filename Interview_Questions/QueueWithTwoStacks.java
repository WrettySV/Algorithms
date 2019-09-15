/* *****************************************************************************
 * Queue with two stacks.
 * Implement a queue with two stacks so that each queue operations
 * takes a constant amortized number of stack operations.
 * Hint:
 * If you push elements onto a stack and then pop them all,
 * they appear in reverse order. If you repeat this process,
 * they're now back in order.
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class QueueWithTwoStacks<Item> implements Iterable<Item> {
    private Stack<Item> stack;
    private Stack<Item> reverseStack;


    public QueueWithTwoStacks() {
        stack = new Stack<Item>();
        reverseStack = new Stack<Item>();

    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item can not be null");
        }
        stack.push(item);
    }

    public void reverseItems(Stack<Item> reverse, Stack<Item> forward) {
        while (!stack.isEmpty()) {
            reverse.push(forward.pop());
        }
    }

    public Item dequeue() {
        if (reverseStack.isEmpty()) {
            if (stack.isEmpty()) throw new java.util.NoSuchElementException("dequeue is empty");
            reverseItems(reverseStack, stack);
        }
        Item item = reverseStack.pop();
        return item;
    }

    public int size() {
        return stack.size() + reverseStack.size();
    }

    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public Iterator<Item> iterator() {
        Stack<Item> printStack = new Stack<Item>();
        printStack = reverseStack;
        for (Item el : stack) {
            printStack.push(el);
        }
        return printStack.iterator();

    }

    public static void main(String[] args) {
        QueueWithTwoStacks<String> que = new QueueWithTwoStacks<String>();
        que.enqueue("to");
        que.enqueue("that");
        que.enqueue("is");
        que.enqueue("the");
        que.enqueue("or");
        for (String s : que)
            StdOut.print(s + " ");
        StdOut.println();
        que.dequeue();
        que.dequeue();
        for (String s : que)
            StdOut.print(s + " ");
        StdOut.println();
        que.enqueue("quest");
        for (String s : que)
            StdOut.print(s + " ");
        StdOut.println();
        que.dequeue();
        que.enqueue("or");
        que.enqueue("not");

        for (String s : que)
            StdOut.print(s + " ");

    }


}
