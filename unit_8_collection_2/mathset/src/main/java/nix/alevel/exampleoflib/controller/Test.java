package nix.alevel.exampleoflib.controller;

import nix.alevel.lib.MathSet;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        MathSet<Integer>  a = new MathSet();
        //a.add(12.1);
        a.add(12);
        a.add(2);
        a.add(1);
        a.add(1);
        Iterator<Integer> iterator = a.iterator();
        while (iterator.hasNext())
            System.out.print("["+iterator.next()+"]");
        System.out.println();
        a.sortDesc();

        while (iterator.hasNext())
            System.out.print("["+iterator.next()+"]");
        System.out.println();
    }
}
