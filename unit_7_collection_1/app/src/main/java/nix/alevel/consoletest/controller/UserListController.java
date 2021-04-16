package nix.alevel.consoletest.controller;

import nix.alevel.OrderedList;
import nix.alevel.consoletest.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserListController {
    private static boolean t =true;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void run() throws IOException {
        List<User> users = new OrderedList();
        while (t) {
            AppController.menu();
            switch (reader.readLine()) {
                case "1": {
                    users.add(new User(readUser()));
                    break;
                }
                case "2": {
                    User u = new User(readUser());
                    try {
                        users.remove(u);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No such element");
                    }
                    break;
                }
                case "3": {
                    System.out.println("How many elements do you want to add?");
                    int count = IntegerListController.readInt();
                    List<User> userList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        userList.add(new User(readUser()));
                    }
                    users.addAll(userList);
                    break;
                }
                case "4": {
                    System.out.println("This method remove all object, that you want, from initial collection");
                    System.out.println("How many elements do you want to remove?");
                    int count = IntegerListController.readInt();
                    List<User> userList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        userList.add(new User(readUser()));
                    }
                    users.removeAll(userList);
                    break;
                }
                case "5": {
                    System.out.println("This method retain all object, that you want, in initial collection");
                    System.out.println("How many elements do you want to retain?");
                    int count = IntegerListController.readInt();
                    List<User> userList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        userList.add(new User(readUser()));
                    }
                    users.retainAll(userList);
                    break;
                }
                case "6": {
                    User user = new User(readUser());
                    if (users.contains(user)) {
                        System.out.println("Element in collection: " + user);
                    } else System.out.println("No such element in collection");
                    System.out.println("");
                    break;
                }
                case "7": {
                    System.out.println("Contains? " + users.contains(new User(readUser())));
                    break;
                }
                case "8": {
                    users.forEach(System.out::println);
                    break;
                }
                case "0": {
                    t = false;
                }
            }
        }
    }
    private static String readUser() throws IOException {
        System.out.println("Enter user`s name: ");
        return reader.readLine();
    }
}
