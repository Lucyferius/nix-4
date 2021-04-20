#MathSet

# It is a custom MathSet, which works with class Number and all its heirs
### MathSet has a structure of dynamic array.
###
### MathSet API

public class MathSet<E extends Number> implements MathSetCollection<E> {

    public MathSet(){}
    public MathSet(int capacity){}
    public MathSet(Number[] numbers){}
    public MathSet(E[] ... numbers){}
    private void pullElementsFromNumberArray(E[] numbers) {}
    public MathSet(MathSet numbers){}
    public MathSet(MathSet ... numbers){}

    public int size(){}
    public void add(E n) {}
    public boolean contains(E n){}
    public final void add(E... n) {}
    public void join(MathSet ms) {}
    public void join(MathSet... ms) {}
    public void sortDesc() {}
    public void sortDesc(int firstIndex, int lastIndex) {}
    public void sortDesc(E value) {}
    public void sortAsc() {}
    public void sortAsc(int firstIndex, int lastIndex) {}
    public void sortAsc(E value) {}
    public Number get(int index) {}
    public Number getMax() {}
    public Number getMin() {}
    public Number getAverage() {}
    public Number getMedian() {}
    public Number[] toArray() {}
    public Number[] toArray(int firstIndex, int lastIndex) {}
    public MathSet<E> squash(int firstIndex, int lastIndex) {}
    public void clear() {}
    public void clear(Number[] numbers) {}
    public int indexOf(E o) {}
    public Iterator<E> iterator(){}

    private class Itr implements Iterator<E> {
        Itr() {}
        public boolean hasNext() {}
        public E next() {}
    }
}

### In MathSet is used merge sort, because of it:
#### - reliability
#### - simplicity
#### - rapidity

