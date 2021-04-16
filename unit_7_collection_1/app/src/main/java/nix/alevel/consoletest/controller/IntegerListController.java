package nix.alevel.consoletest.controller;

import nix.alevel.OrderedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntegerListController {
    private static boolean t =true;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void run() throws IOException {
        List<Integer> integers = new OrderedList();
        while (t){
            AppController.menu();
            switch (reader.readLine()){
                case "1":{
                    integers.add(readInt());
                    break;
                }
                case "2":{
                    Integer n = readInt();
                    try {
                        integers.remove(n);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("No such element");
                    }

                    break;
                }
                case "3":{
                    System.out.println("How many elements do you want to add?");
                    int count = readInt();
                    List<Integer> ints = new ArrayList<>();
                    for (int i =0; i<count;i++){
                        ints.add(readInt());
                    }
                    integers.addAll(ints);
                    break;
                }
                case "4":{
                    System.out.println("This method remove all object, that you want, from initial collection");
                    System.out.println("How many elements do you want to remove?");
                    int count = readInt();
                    List<Integer> ints = new ArrayList<>();
                    for (int i =0; i<count;i++){
                        ints.add(readInt());
                    }
                    integers.removeAll(ints);
                    break;
                }
                case "5":{
                    System.out.println("This method retain all object, that you want, in initial collection");
                    System.out.println("How many elements do you want to retain?");
                    int count = readInt();
                    List<Integer> ints = new ArrayList<>();
                    for (int i =0; i<count;i++){
                        ints.add(readInt());
                    }
                    integers.retainAll(ints);
                    break;
                }
                case "6":{
                    Integer integer = readInt();
                    if(integers.contains(integer)){
                        System.out.println("Element in collection: " + integer);
                    }else System.out.println("No such element in collection");
                    System.out.println("");
                    break;
                }
                case "7":{
                    System.out.println("Contains? " +integers.contains(readInt()));
                    break;
                }
                case "8":{
                    Iterator<Integer> iterator = integers.iterator();
                    while (iterator.hasNext()){
                        System.out.print("[" + iterator.next() +"]");
                    }
                    System.out.println();
                    break;
                }
                case "0":{
                    t = false;
                }
            }
        }
    }
    public static int readInt() throws IOException {
        boolean check = true;
        int res;
        while (check){
            System.out.println("Enter integer: ");
            String number = reader.readLine();
            try {
                res = Integer.parseInt(number);
                check = false;
                return res;
            }catch (NumberFormatException e){
                System.out.println("Enter the number");
            }
        }
        throw new RuntimeException();
    }
}
