package nix.alevel.exampleoflib.controller;

import nix.alevel.lib.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class DoubleController {
    private static boolean t =true;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void run() throws IOException {
        MathSet<Double> set = new MathSet<>();
        while (t){
            AppController.menu();
            switch (reader.readLine()){
                case "1":{
                    double a = readDouble();
                    if(set.contains(a))
                        System.out.println("This element already exist in set");
                    else { set.add(a);
                        System.out.println("Element is added");
                    }
                    break;
                }
                case "2":{
                    System.out.println("This method join entered set with the existed set");
                    System.out.println("How much element to do want to be added in set?");
                    int count = IntegerController.readInt();
                    System.out.println("Start entering");
                    MathSet<Double> newSet = new MathSet<>();
                    for (int i = 0; i <count ; i++) {
                        newSet.add(readDouble());
                    }
                    print(newSet);
                    set.join(newSet);
                    System.out.println("Joined!");
                    break;
                }
                case "3":{
                    boolean c = true;
                    while (c){
                        AppController.menuOfSorting();
                        switch (reader.readLine()){
                            case "1":{
                                set.sortDesc();
                                System.out.println("Sorted!");
                                print(set);
                                break;
                            }
                            case "2":{
                                System.out.println("Enter first index:");
                                int f = IntegerController.readInt();
                                System.out.println("Enter last index:");
                                int s = IntegerController.readInt();
                                try {
                                    set.sortDesc(f, s);
                                    System.out.println("Sorted!");
                                    print(set);
                                }catch (IndexOutOfBoundsException  | IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                    System.out.println("Try again");
                                }
                                break;
                            }
                            case "3":{
                                System.out.println("Enter element from which start to sort:");
                                double f = readDouble();
                                try {
                                    set.sortDesc(f);
                                    System.out.println("Sorted!");
                                    print(set);
                                } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Try again");
                                }
                                break;
                            }
                            case "4":{
                                set.sortAsc();
                                System.out.println("Sorted!");
                                print(set);
                                break;
                            }
                            case "5": {
                                System.out.println("Enter first index:");
                                int f = IntegerController.readInt();
                                System.out.println("Enter last index:");
                                int s = IntegerController.readInt();
                                try {
                                    set.sortAsc(f, s);
                                    System.out.println("Sorted!");
                                    print(set);
                                }catch (IndexOutOfBoundsException | IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                    System.out.println("Try again");
                                }
                                break;


                            }
                            case "6": {
                                System.out.println("Enter element from which start to sort:");
                                double f = readDouble();
                                try {
                                    set.sortAsc(f);
                                    System.out.println("Sorted!");
                                    print(set);
                                } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Try again");
                                }
                                break;
                            }
                            case "0":{
                                c = false;
                            }
                        }
                    }
                    break;
                }
                case "4":{
                    boolean c = true;
                    while (c){
                        AppController.getMenu();
                        switch (reader.readLine()){
                            case "1":{
                                System.out.println("Max = " + set.getMax());
                                break;
                            }
                            case "2":{
                                System.out.println("Min = " + set.getMin());
                                break;
                            }
                            case "3":{
                                System.out.println("Average = " + set.getAverage());
                                break;
                            }
                            case "4":{
                                System.out.println("Median = " + set.getMedian());
                                break;
                            }
                            case "0":{
                                c = false;
                            }
                        }
                    }
                    break;
                }
                case "5":{
                    System.out.println("This method remove all elements in interval [firstIndex, lastIndex]");
                    System.out.println("Enter first index:");
                    int f = IntegerController.readInt();
                    System.out.println("Enter last index:");
                    int s = IntegerController.readInt();
                    try {
                        set = set.squash(f, s);
                        print(set);

                    }catch (IndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        System.out.println("Try again");
                    }
                    break;
                }
                case "6":{
                    print(set);
                    break;
                }
                case "0":{
                    t = false;
                }
            }
        }
    }
    public static void print(MathSet set){
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext())
            System.out.print("["+iterator.next()+"]");
        System.out.println();
    }
    public static double readDouble() throws IOException {
        boolean check = true;
        double res;
        while (check){
            System.out.println("Enter double: ");
            String number = reader.readLine();
            try {
                res = Double.parseDouble(number);
                check = false;
                return res;
            }catch (NumberFormatException e){
                System.out.println("Enter the number");
            }
        }
        throw new RuntimeException();
    }
}


