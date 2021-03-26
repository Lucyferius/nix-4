package nix.alevel.util.uniquenumbers;

import java.util.ArrayList;
import java.util.List;

public class UniqueNumbersFinder {
    public static int findUniqueNumbersInArray(List<? extends Number> array){
        List <Number> uniqueNumbers = new ArrayList<>();
        int counter = 0;
        for (Number number: array){
            if (!uniqueNumbers.contains(number)){
                uniqueNumbers.add(number);
                counter++;
            }
        }
        return counter;
    }
}
