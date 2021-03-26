package nix.alevel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static ConsoleHelper instance;
    private ConsoleHelper(){
    }
    public static ConsoleHelper getInstance(){
        if(instance == null){
            instance = new ConsoleHelper();
        }
        return instance;
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
    public List<Integer> makeIntegerArrayFromString(String arrayString) {
        try {
            Pattern pattern = Pattern.compile("\\b[\\d]+\\b");
            Matcher matcher = pattern.matcher(arrayString);
            List<Integer> result = new ArrayList<>();
            while (matcher.find()){
                //System.out.println( matcher.group(0));
                result.add(Integer.parseInt(matcher.group()));
            }
            return result;
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void printArray(List<Integer> array){
        System.out.println("Your array:");
        for(Integer i: array)
            System.out.print("["+i+"]");
        System.out.println();
    }

}
