package nix.oop.impl;

import nix.oop.ConsoleHelperService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class ConsoleHelper implements ConsoleHelperService {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public void writeMessage(String message) {
        System.out.println(message);
    }
    public void writeResult(BigDecimal message) {
        System.out.println("Result: " + message);
    }

    public String readString() throws IOException {
        return bufferedReader.readLine();
    }
    public int readInteger() throws IOException{
        writeMessage("Your choice: ");
        String text = readString();
        try {
            return Integer.parseInt(text.trim());
        }catch (IllegalArgumentException e){
            System.err.println("You enter a string.");
            e.printStackTrace();
        }
        throw new  RuntimeException("Wrong");
    }

    public BigDecimal readDecimal() throws IOException {
        writeMessage("Enter BigDecimal: ");
        String text = readString();
        try {
            return new BigDecimal(String.valueOf(text));
        }catch (IllegalArgumentException e){
            System.err.println("You enter a string.");
            e.printStackTrace();
        }
        throw new  RuntimeException("Wrong");
    }

}
