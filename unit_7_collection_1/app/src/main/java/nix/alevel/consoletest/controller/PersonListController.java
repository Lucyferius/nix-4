package nix.alevel.consoletest.controller;

import nix.alevel.OrderedList;
import nix.alevel.consoletest.entity.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PersonListController {
    private static boolean t =true;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void run() throws IOException {
        List<Person> people = new OrderedList(Person.personComparator);
        while (t) {
            AppController.menu();
            switch (reader.readLine()) {
                case "1": {
                    people.add(new Person(readName(), readAge()));
                    break;
                }
                case "2": {
                    Person p = new Person(readName(), readAge());
                    try {
                        people.remove(p);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No such element");
                    }
                    break;
                }
                case "3": {
                    System.out.println("How many elements do you want to add?");
                    int count = IntegerListController.readInt();
                    List<Person> personList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        personList.add(new Person(readName(), readAge()));
                    }
                    people.addAll(personList);
                    break;
                }
                case "4": {
                    System.out.println("This method remove all object, that you want, from initial collection");
                    System.out.println("How many elements do you want to remove?");
                    int count = IntegerListController.readInt();
                    List<Person> personList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        personList.add(new Person(readName(), readAge()));
                    }
                    people.removeAll(personList);
                    break;
                }
                case "5": {
                    System.out.println("This method retain all object, that you want, in initial collection");
                    System.out.println("How many elements do you want to retain?");
                    int count = IntegerListController.readInt();
                    List<Person> personList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        personList.add(new Person(readName(), readAge()));
                    }
                    people.retainAll(personList);
                    break;
                }
                case "6": {
                    Person p = new Person(readName(), readAge());
                    if (people.contains(p)) {
                        System.out.println("Element in collection: " + p);
                    } else System.out.println("No such element in collection");
                    System.out.println("");
                    break;
                }
                case "7": {
                    System.out.println("Contains? " + people.contains(new Person(readName(), readAge())));
                    break;
                }
                case "8": {
                    people.forEach(System.out::println);
                    break;
                }
                case "0": {
                    t = false;
                }
            }
        }
    }
    private static String readName() throws IOException {
        System.out.println("Enter person`s name: ");
        return reader.readLine();
    }
    public static int readAge() throws IOException {
        boolean check = true;
        int res;
        while (check){
            System.out.println("Enter age: ");
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
