/* *****************************************************************************
 * Stack with max. Create a data structure that efficiently supports the stack
 * operations (push and pop) and also a return-the-maximum operation.
 * Assume the elements are real numbers so that you can compare them.
 * Hint: Use two stacks, one to store all of the items and a second
 * stack to store the maximums.
 **************************************************************************** */


class realNumberStack<Item> {
    private Item[] elements;
    private int size;

    public realNumberStack() {
        elements = (Item[]) new Object[1];
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public Item pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty!");
        }
        Item item = elements[--size];
        elements[size] = null;
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return item;
    }

    public void push(Item item) {
        if (size == elements.length) resize(2 * size);
        elements[size++] = item;
    }


    private void resize(int capacity) {
        Item copy[] = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }


    public Item lastItem() {
        if (isEmpty()) throw new IllegalArgumentException("Stack is empty!");
        int last = size;
        return elements[--last];
    }
}

public class StackWithMax {
    private realNumberStack<Double> stack;
    private realNumberStack<Double> maxStack;

    StackWithMax() {
        stack = new realNumberStack<Double>();
        maxStack = new realNumberStack<Double>();
    }


    public void push(double realNumber) {
        stack.push(realNumber);
        if (maxStack.isEmpty()) maxStack.push(realNumber);
        else if (realNumber > maxStack.lastItem()) maxStack.push(realNumber);
    }


    public double pop() {
        double item = stack.pop();
        if (item == maxStack.lastItem()) maxStack.pop();
        return item;
    }


    public double max() {
        return maxStack.lastItem();
    }


    public static void main(String[] args) {
    }

}
