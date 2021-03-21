package nix.alevel.setofoperations;

import nix.alevel.ConsoleHelperService;
import nix.alevel.StringReverseService;
import nix.alevel.factory.ConsoleFactory;
import nix.alevel.factory.StringReverseFactory;

import java.io.IOException;

public class SetOfOperations {
    private StringReverseService stringReverser = StringReverseFactory.create();
    private ConsoleHelperService consoleHelper = ConsoleFactory.create();
    public void reverseAllString() throws IOException {
        consoleHelper.writeMessage("Enter a string: ");
        String string = consoleHelper.readString();
        System.out.println(stringReverser.reverse(string));
    }
    public void reverseSubstring() throws IOException {
        consoleHelper.writeMessage("Enter a string: ");
        String src = consoleHelper.readString();
        consoleHelper.writeMessage("Enter a substring: ");
        String dest = consoleHelper.readString();
        System.out.println(stringReverser.reverse(src,dest));
    }
    public void reverseBetweenTwoIndexes()throws IOException {
        consoleHelper.writeMessage("Enter a string: ");
        String src = consoleHelper.readString();
        consoleHelper.writeMessage("Enter a first index: ");
        int firstIndex = consoleHelper.readInteger();
        consoleHelper.writeMessage("Enter a last index: ");
        int lastIndex = consoleHelper.readInteger();
        System.out.println(stringReverser.reverse(src,firstIndex,lastIndex));
    }
    public void menu(){
        consoleHelper.writeMessage("1. Task 1: reverse each word in the string"+
                "\n2. Task 2: reverse a substring in the string"+
                "\n3. Task 3: reverse a substring in interval (from 0 to lastIndex-1)"+
                "\n0. Press 0 to exit");
    }
    public int chooseFromMenu() throws IOException {
        consoleHelper.writeMessage("Your choice: ");
        return consoleHelper.readInteger();
    }
}
