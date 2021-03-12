package tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Task2 {
    public static Map<Character, Integer> findMatchesAmongLetters(BufferedReader bufferedReader) throws IOException {
        System.out.println("Введите строку: ");
        String out = bufferedReader.readLine();
        String title = out.toLowerCase();
        System.out.println("Повторяющиеся символы:");
        Map<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i = 0; i < title.length(); ++i) {
            char c = title.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
    public static void printMapOfLetters(Map<?,Integer> map){
        for (Map.Entry<?, Integer> pair : map.entrySet()) {
            if(pair.getValue()>1)
                System.out.println("'"+pair.getKey()+"'" +" - "+pair.getValue() );
        }
    }
}
