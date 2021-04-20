package nix.alevel.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MathSet <E extends Number> implements MathSetCollection<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Number[] EMPTY_ELEMENTDATA = {};
    private static final Number[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int size = 0;
    transient Number[] elementData;

    public MathSet(){
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public MathSet(int capacity){
        if(capacity>0)
            this.elementData =new Number[capacity];
        else if (capacity == 0)
            this.elementData = EMPTY_ELEMENTDATA;
        else throw new IllegalArgumentException("Illegal Capacity: "+
                    capacity);
    }
    public MathSet(Number[] numbers){
        if ((size = numbers.length) != 0) {
           this.elementData = numbers;
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }
    public MathSet(E[] ... numbers){
        for (Number[] arr : numbers)
            if(arr == null)
                throw new NullPointerException("One of arrays is null");
        int newSize = 0;
        for (E[] number : numbers) newSize += number.length;
        this.elementData = new Number[newSize];
        for (E[] number : numbers) pullElementsFromNumberArray(number);

    }
    private void pullElementsFromNumberArray(E[] numbers) {
        size+= numbers.length;
        for (E number : numbers) {
            if (number == null) throw new NullPointerException("Element couldn`t be null ");
                add(number);
        }
    }
    public MathSet(MathSet numbers){
        Number[] a = numbers.toArray();
        if ((size = a.length) != 0)
            elementData = a;
        else
            elementData = EMPTY_ELEMENTDATA;
    }
    public MathSet(MathSet ... numbers){
        elementData  = EMPTY_ELEMENTDATA;
        int newSize = 0;
        for (MathSet number : numbers) {
            E[] a = (E[])number.toArray();
            newSize += a.length;
            add(a);
        }
        size = newSize;
    }
    public int size(){
        return size;
    }
    @Override
    public void add(E n) {
        if(n == null) throw new NullPointerException("Number cann`t be null");
        if(!contains(n))
            add(n, elementData, size);
    }
    private void add(E n, Number[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = n;
        size = s + 1;
    }
    public boolean contains(E n){
        for (int i = 0; i < size; i++) {
            if(n.equals(get(i))) return true;
        }
        return false;
    }
    @SafeVarargs
    @Override
    public final void add(E... n) {
        for (E e : n)
            add(e, elementData, size);

    }
    private Number[] grow() {
        return grow(size + 1);
    }

    private Number[] grow(int minCapacity) {
        int size = newCapacity(minCapacity);
        Number [] a = new Number[size];
        for (int index = 0; index < elementData.length; index++) {
            a[index] = elementData[index];
        }
        return (elementData=a);
    }

    private int newCapacity(int minCapacity) {
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
    @Override
    public void join(MathSet ms) {
        E[] a = (E[]) ms.toArray();
        for (int i = 0; i < ms.size(); i++) {
            add(a[i]);
        }
    }

    @Override
    public void join(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            E[] a = (E[]) ms[i].toArray();
            for (int j = 0; j < ms[i].size(); j++) {
                add(a[j]);
            }
        }
    }
    private void merge(Number[] arr, int l, int m, int r, boolean asc) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Number[] L = new Number[n1];
        Number[] R = new Number[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            //int compare;
            boolean compare;
            //Comparable<E> el1 = (Comparable<E>) L[i];
            //compare = el1.compareTo((E)R[j]);
            compare = L[i].doubleValue()>=R[j].doubleValue();
            if(asc){
                //if(compare<=0)
                if(!compare) {
                    arr[k] =L[i];
                    i++;
                } else {
                    arr[k] =R[j];
                    j++;
                }
            }else {
                //if(compare>0
                if(compare) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] =  R[j];
                    j++;
                }
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] =R[j];
            j++;
            k++;
        }
    }

    private void mergeSort(Number[] arr,int l,int r, boolean acs){
        if(l>=r){
            return;
        }
        int m =l+ (r-l)/2;
        mergeSort(arr,l,m, acs);
        mergeSort(arr,m+1,r, acs);
        merge(arr,l,m,r, acs);
    }
    private void checkIndexes(int f, int l){
        checkIndex(f, size);
        checkIndex(l, size);
        if(l<f) throw new IllegalArgumentException ("Last index bigger than first");
    }
    @Override
    public void sortDesc() {
        mergeSort(elementData, 0, size-1, false);
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        // до lastIndex включительно
        checkIndexes(firstIndex, lastIndex);
        mergeSort(elementData, firstIndex, lastIndex, false);
    }

    @Override
    public void sortDesc(E value) {
        int index = indexOf(value);
        if(index == -1) throw new IllegalArgumentException("Element not found");
        mergeSort(elementData, index, size-1, false);
    }

    @Override
    public void sortAsc() {
        mergeSort(elementData, 0, size-1, true);
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        // до lastIndex включительно
        checkIndexes(firstIndex, lastIndex);
        mergeSort(elementData, firstIndex, lastIndex, true);
    }

    @Override
    public void sortAsc(E value) {
        int index = indexOf(value);
        if(index == -1) throw new IllegalArgumentException("Element not found");
        mergeSort(elementData, index, size-1, true);
    }

    @Override
    public Number get(int index) {
        checkIndex(index, size);
        return elementData[index];
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("index "+ index +" for length " +length);
    }

    @Override
    public E getMax() {
        Number[] sorted = new Number[size];
        System.arraycopy(this.elementData,0, sorted, 0, size);
        mergeSort(sorted, 0, size-1, false);
        return (E)sorted[0];
    }

    @Override
    public E getMin() {
        Number[] sorted = new Number[size];
        System.arraycopy(this.elementData,0, sorted, 0, size);
        mergeSort(sorted, 0, size-1, true);
        return (E) sorted[0];
    }

    @Override
    public E getAverage() {
        // divide bg1 with bg2 with 3 scale
        return (E) this.sumAll().divide(new BigDecimal(size), 3, RoundingMode.CEILING);

    }
    private BigDecimal sumAll(){
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < size; i++)
            sum = new BigDecimal(String.valueOf(sum)).add(new BigDecimal(this.elementData[i].toString()));
        return sum;
    }

    @Override
    public E getMedian() {
        if(size%2 !=0) return (E)get(size/2);
        else {
            BigDecimal sum = new BigDecimal(String.valueOf(get(size/2 - size%2 -1))).add(new BigDecimal(String.valueOf(get(size/2 - size%2 ))));
            return (E)(sum.divide(new BigDecimal(2), 2, RoundingMode.CEILING));
        }
    }

    @Override
    public E[] toArray() {
        return (E[])elementData;
    }

    @Override
    public E[] toArray(int firstIndex, int lastIndex) {
        checkIndex(firstIndex, size);
        checkIndex(lastIndex, size);
        // до lastIndex включительно
        Number[] a  = new Number[lastIndex-firstIndex+1];
        System.arraycopy(elementData, 0, a,firstIndex, lastIndex);
        return (E[])a;
    }

    @Override
    public MathSet<E> squash(int firstIndex, int lastIndex) {
        checkIndex(firstIndex, size);
        checkIndex(lastIndex, size);
        MathSet<E> set = new MathSet<>(size - lastIndex - firstIndex +1);
        for (int i = 0; i <firstIndex ; i++) {
            set.add((E)elementData[i]);
        }
        for (int i = lastIndex+1; i <size ; i++) {
            set.add((E)elementData[i]);
        }
        return set;
    }

    @Override
    public void clear() {
        final Number[] arr = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            arr[i] = null;
    }

    @Override
    public void clear(Number[] numbers) {
        final Number[] arr = elementData;
        for (int to = size, i = size = 0; i < to; i++) {
            for (Number number : numbers) {
                if (arr[i].equals(number)) {
                    arr[i] = null;
                    break;
                }
            }
        }
    }

    public int indexOf(E o) {
        return indexInRange(o, 0, size);
    }
    private int indexInRange(E o, int start, int end){
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.doubleValue() == elementData[i].doubleValue())
                    return i;
            }
        }
        return -1;
    }
    public Iterator<E> iterator(){
        return new Itr();
    }
    private class Itr implements Iterator<E> {
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
            Number[] elementData = MathSet.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
    }
}
