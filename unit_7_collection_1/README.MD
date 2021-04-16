#OrderedList

# It is a custom List implementation
### OrderedList is a dynamic array with sorted elements.
### Object to input must implement interface Comparable<>, or it should be realized Comparator based on it.
###
### API

public class OrderedList<E> implements List<E> {

    public OrderedList(int initialCapacity);
    public OrderedList(Comparator<? super E> comparator);
    public OrderedList();
    public OrderedList(Collection<? extends E> c);
    public OrderedList(Collection<? extends E> c, Comparator<E> cmp);
    
    public int size();
    public boolean isEmpty();
    public boolean contains(Object o);
    public Object[] toArray();
    public <T> T[] toArray(T[] a);
    public int indexOf(Object o) ;
    public int lastIndexOf(Object o);
    public void clear();
    public boolean add(E e);
    public boolean remove(Object o);
    public boolean containsAll(Collection<?> c);
    public boolean addAll(Collection<? extends E> c);
    public boolean removeAll(Collection<?> c);
    public boolean retainAll(Collection<?> c);
    public E get(int index);
    public E remove(int index);
    public Iterator<E> iterator();
    public ListIterator<E> listIterator();
    public ListIterator<E> listIterator(int index) 
    public List<E> subList(int fromIndex, int toIndex);
    
        //DEPRECATED METHODS

    @Deprecated
    public E set(int index, E element);
    @Deprecated
    public void add(int index, E element);
    @Deprecated
    public boolean addAll(int index, Collection<? extends E> c);
}


### In OrderedList is used merge sort, because of it:
#### - reliability
#### - simplicity
#### - rapidity

