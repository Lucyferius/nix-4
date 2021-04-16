package nix.alevel;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class OrderedList<E> implements List<E> {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int size;
    transient Object[] elementData;
    protected transient int modCount = 0;

    public OrderedList(int initialCapacity) {
        this.comparator = null;
        if (initialCapacity > 0) {
            this.elementData = new Comparable[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    public OrderedList(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public OrderedList() {
        this.comparator = null;
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public OrderedList(Collection<? extends E> c) {
        this.comparator = null;
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == OrderedList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }
    public OrderedList(Collection<? extends E> c, Comparator<E> cmp) {
        this.comparator = cmp;
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == OrderedList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    @Override
    public int indexOf(Object o) {
        return indexInRange(o, 0, size);
    }
    private int indexInRange(Object o, int start, int end){
        Object[] es = elementData;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }
    private int lastIndexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public void clear() {
        modCount++;
        final Object[] arr = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            arr[i] = null;
    }

    private void sort() {
        if (size == 0 || size ==1)
            return;
        if(comparator!=null)
            mergeSort(elementData, 0, size-1, false);
        else if(Comparable.class.isAssignableFrom(elementData[0].getClass()))
            mergeSort(elementData, 0, size-1, true);
        else throw new ClassCastException("Objects must implement comparable");
        modCount++;
    }
    private void merge(Object arr[], int l, int m, int r, boolean isComparable) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Object L[] = new Object[n1];
        Object R[] = new Object[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            int compare;
            if(isComparable){
                Comparable<E> el1 = (Comparable<E>) L[i];
                compare = el1.compareTo((E)R[j]);
            }
            else compare = comparator.compare((E)L[i], (E) R[j]);

            if(compare<=0) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void mergeSort(Object arr[],int l,int r, boolean isComparable){
        if(l>=r){
            return;
        }
        int m =l+ (r-l)/2;
        mergeSort(arr,l,m, isComparable);
        mergeSort(arr,m+1,r, isComparable);
        merge(arr,l,m,r, isComparable);
    }

    @Override
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        if(size!=1)
            sort();
        return false;
    }
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private Object[] grow() {
        return grow(size + 1);
    }
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }
    private int checkIndex(int index, int length) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("index "+ index +" for length " +length);
        return index;
    }
    @Override
    public boolean remove(Object o) {
        int index  = indexOf(o);
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] a = (E[]) c.toArray();
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] a = (E[]) c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        if (numNew > size)
            this.elementData = grow(size + numNew);

        if(comparator!=null) {
            System.arraycopy(a, 0, this.elementData, size, numNew);
            mergeSort(this.elementData, 0, numNew-1, false);
        } else if(Comparable.class.isAssignableFrom(a[0].getClass())){
            System.arraycopy(a, 0, this.elementData, size, numNew);
            mergeSort(this.elementData, 0, numNew-1, true);
        } else throw new ClassCastException("Objects must implement comparable");

        size += numNew;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return retainOrRemoveOperation(c, false, 0, size);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return retainOrRemoveOperation(c, true, 0, size);
    }
    private boolean retainOrRemoveOperation(Collection<?> c, boolean logic, int from, int end) {
        Objects.requireNonNull(c);
        final Object[] elements = elementData;
        int r;
        for (r = from;; r++) {
            if (r == end)
                return false;
            if (c.contains(elements[r]) != logic)
                break;
        }
        int w = r++;
        try {
            for (Object e; r < end; r++)
                if (c.contains(e = elements[r]) == logic) {
                    elements[w++] = e;
                }
        } catch (Throwable ex) {
            System.arraycopy(elements, r, elementData, w, end - r);
            w += end - r;
            throw ex;
        } finally {
            System.arraycopy(elements, end, elementData, w, size - end);
            for (int to = size, i = (size -= end - w); i < to; i++)
                elements[i] = null;
            System.out.println("size = " +size);
        }
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);
        return (E)elementData[index];
    }


    @Override
    public E remove(int index) {
        checkIndex(index, size);
        E element = get(index);
        for (int i =index; i<size; i++){
            elementData[i] = elementData[i+1];
        }
        elementData[size-1] = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIterator<E>() {
            int cursor = index;
            int lastRet = -1;

            @Override
            public boolean hasNext() {
                return cursor!=size;
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= size)
                    throw new NoSuchElementException();
                Object[] elementData = OrderedList.this.elementData;
                if (i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return (E) elementData[lastRet = i];
            }

            @Override
            public boolean hasPrevious() {
                return cursor!=0;
            }

            @Override
            public E previous() {
                if (cursor<0)
                    throw new NoSuchElementException();
                Object[] elementData = OrderedList.this.elementData;
                cursor --;
                return (E)OrderedList.this.elementData[cursor+1];
            }

            @Override
            public int nextIndex() {
                return cursor;
            }

            @Override
            public int previousIndex() {
                return cursor-1;
            }

            @Override
            public void remove() {
                if (cursor < 0)
                    throw new IllegalStateException();
                OrderedList.this.remove(cursor);
                if (cursor + 1 >= size)
                    cursor = -1;
            }

            @Override
            @Deprecated
            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(E e) {
                OrderedList.this.add(e);
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sub = new OrderedList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            sub.add((E) elementData[i]);
        }
        return sub;
    }
    private class Itr implements Iterator<E>{
        int cursor;
        int lastRet = -1;

        Itr() {}

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = OrderedList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
    }




        //DEPRECATED METHODS

    @Override
    @Deprecated
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
    @Override

    @Deprecated
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
}
