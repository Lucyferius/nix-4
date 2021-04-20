package nix.alevel.exampleoflib.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppController {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void run() throws IOException {
        while (true){
            greeting();
            switch (reader.readLine()){
                case "1":
                    IntegerController.run();
                    break;
                case "2":
                    DoubleController.run();
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Illegal choice");
            }

        }
    }
    private static void greeting() {
        System.out.println("Hello, dear user, it`s a test of some methods from MathSet library.\n" +
                "MathSet can work with class Number and all its heirs. \n" +
                "This test was created to get you acquainted with the main methods of custom library.\n" +
                "More information you can read in README.MD\n" +
                "Please, choose the logic for testing\n" +
                "\t1. Test library on Integer values\n" +
                "\t2. Test library on Double values" +
                "\n Press 0 to Exit" );
    }
    public static void menu(){
        System.out.println("1. Add\n" +
                "2. Join set\n" +
                "3. Sort\n" +
                "4. Get\n" +
                "5. Squash\n" +
                "6. Print\n" +
                "0. Exit");
    }
    public static void menuOfSorting(){
        System.out.println("1. Sort decs all elements\n" +
                "2. Sort decs from index to index\n" +
                "3. Sort decs begin from element\n" +
                "4. Sort asc all elements\n" +
                "5. Sort asc from index to index\n" +
                "6. Sort asc begin from element\n" +
                "0. Exit");
    }
    public static void getMenu(){
        System.out.println("1. Get max\n" +
                "2. Get min\n" +
                "3. Get average\n" +
                "4. Get median\n" +
                "0. Exit");
    }

}

