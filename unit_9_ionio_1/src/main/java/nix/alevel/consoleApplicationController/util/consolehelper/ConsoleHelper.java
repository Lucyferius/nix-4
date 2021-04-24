package nix.alevel.consoleApplicationController.util.consolehelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bufferedReader;
    private static ConsoleHelper consoleHelper;

    private ConsoleHelper() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static ConsoleHelper getInstance() {
        if ( consoleHelper == null) {
            consoleHelper = new ConsoleHelper();
        }
        return consoleHelper;
    }

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readString() throws IOException {
        return bufferedReader.readLine();
    }
    public int readInteger(){
        int check = 0;
        while (check ==0 ){
            try {
                String text = readString();
                int number = Integer.parseInt(text.trim());
                check = 1;
                return number;
            }catch (IllegalArgumentException e){
                System.err.println("You enter a string.");
            }catch (IOException e){
                System.err.println("Wrong");
            }
        }
        throw new  RuntimeException("Wrong");
    }

}
