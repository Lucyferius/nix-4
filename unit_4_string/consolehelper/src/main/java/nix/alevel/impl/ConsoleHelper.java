package nix.alevel.impl;


import nix.alevel.ConsoleHelperService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper implements ConsoleHelperService {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readString() throws IOException {
        return bufferedReader.readLine();
    }
    public int readInteger() throws IOException{
        String text = readString();
        try {
            return Integer.parseInt(text.trim());
        }catch (IllegalArgumentException e){
            System.err.println("You enter a string.");
            e.printStackTrace();
        }
        throw new  RuntimeException("Wrong");
    }

}
