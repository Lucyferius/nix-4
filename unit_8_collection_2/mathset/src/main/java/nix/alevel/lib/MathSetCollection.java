package nix.alevel.lib;

import nix.alevel.lib.MathSet;

public  interface MathSetCollection <E extends Number> {
    void add(E n);
    void add(E ... n);
    void join(MathSet ms);
    void join(MathSet ... ms);
    void sortDesc();
    void sortDesc(int firstIndex, int lastIndex);
    void sortDesc(E value);
    void sortAsc();
    void sortAsc(int firstIndex, int lastIndex);
    void sortAsc(E value);
    Number get(int index);
    Number getMax();
    Number getMin();
    Number getAverage();
    Number getMedian();
    Number[] toArray();
    Number[] toArray(int firstIndex, int lastIndex);
    MathSet squash(int firstIndex, int lastIndex);
    void clear();
    void clear(E[] numbers);
}
