import java.util.Iterator;
public class Stack<Item> implements Iterable<Item>{

    private int count;
    private Item[] a;
    private String stringOutput = "";


    public Stack() { //Constructor with initial stack size of 2
        a = (Item[]) new Object[2];
    }

    public void push(Item item) { //Push an item onto the stack
        if (count == a.length)
            upsize();
        a[count++] = item;
    }

    public void upsize() { //Double the size of the stack if the stack is full
        Item[] temp = (Item[]) new Object[2*a.length];
        for (int i=0; i < count; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Item pop() { //Pop an item of the stack
        Item item = a[--count];
        a[count] = null;

        if (!isEmpty() && count == (a.length/4))
            downsize();

        return item;
    }

    public void downsize() { //Halve the size of the stack if stack is 1/4 full
        Item[] temp = (Item[]) new Object[a.length/2];
        for (int i=0; i < count; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty() { //Check if stack is empty
        return count == 0;
    }

    public int size() { //Returns size of the stack
        return count;
    }

    public String toString() { //toString implementation
        while (!isEmpty()) {
            String test = ""+pop();
            if (!test.equals("null"))
                stringOutput += ""+pop()+"\n";
        }

        return("Stack contains " +count+" items:\n " + stringOutput);
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = count;
        public boolean hasNext() {
          return i > 0;
        }
        public Item next() {
            return a[--i];
        }
        public void remvove() {}
    }
}
