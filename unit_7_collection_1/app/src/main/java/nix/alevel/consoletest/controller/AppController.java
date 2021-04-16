package nix.alevel.consoletest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppController {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public void run() throws IOException {
        while (true){
            try {
                switch (greeting()){
                    case "1":
                        try {
                            IntegerListController.run();
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            UserListController.run();
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            PersonListController.run();
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Enter the number from ordered");
                        break;
                    case "0": System.exit(0);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    private String greeting() throws IOException {
        System.out.println("Hello, dear user, it`s a test of some methods from OrderedList library.\n" +
                "Please, choose the logic for testing\n" +
                "\t1. Test library on Integer values\n" +
                "\t2. Test library on entity User" +
                "\n\t\t\t{User implements Comparable" +
                "\n\t\t\t User has one field name (as name you can enter any string: numbers, letters, tags)" +
                "\n\t\t\t Users comparing by names}" +
                "\n\t3. Test library on entity Person" +
                "\n\t\t\t{Person doesn`t implements Comparable, but has Comparator" +
                "\n\t\t\t Person has two fields name and age (as name you can enter any string: numbers, letters, tags)" +
                "\n\t\t\t Persons comparing by age}" +
                "\n Press 0 to Exit" );
        return reader.readLine();
    }
    public static void menu(){
        System.out.println("1. Add\n" +
                "2. Remove\n" +
                "3. Add all\n" +
                "4. Remove all\n" +
                "5. Retain all\n" +
                "6. Get object from collection\n" +
                "7. Contains object in collection\n" +
                "8. Print collection\n" +
                "0. Exit");
    }
}

