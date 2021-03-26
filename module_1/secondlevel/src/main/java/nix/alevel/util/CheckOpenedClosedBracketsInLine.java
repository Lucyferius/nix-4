package nix.alevel.util;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckOpenedClosedBracketsInLine {
    private static final char[] openBrackets = {'(', '{', '['};
    private static final char[] closedBrackets = {')', '}', ']'};

    public static boolean checkClosedBracketsInLine(String str){
        int hasAnyBrackets = 0;
        if(str.isBlank()) return true;

        char[] strChar = str.toCharArray();

        // Проверка есть ли в строке хотя бы одна скобка одна открытая скробка
        // Если нет - то строка не валидная автоматически
        for (char symbol: openBrackets){
            if(isSymbolInArray(symbol, strChar)){
                hasAnyBrackets++;
                break;
            }
        }
        // Если в нет хотя бы одной открытой, проверяем есть ли хотя бы одна закрытая
        // Если нет - то строка не валидная автоматически
        if(hasAnyBrackets == 0) {
            for (char symbol : closedBrackets) {
                if (isSymbolInArray(symbol, strChar)) {
                    hasAnyBrackets++;
                    break;
                }
            }
        }
        if(hasAnyBrackets == 0) return false;

        ArrayList<Character> brackets = new ArrayList<>();
        for(int i=0; i<str.length();i++){
            char currentSymbol = strChar[i];
            if(isSymbolInArray(currentSymbol, openBrackets))
                brackets.add(currentSymbol);
            else {
                int lastSymbolInBracketsArray = indexOf(currentSymbol, closedBrackets);
                if(lastSymbolInBracketsArray!=-1){
                    if (brackets.isEmpty())
                        return false;
                    if (brackets.remove(brackets.size()-1) != openBrackets[lastSymbolInBracketsArray])
                        return false;
                }
            }
        }
        return brackets.isEmpty();
    }
    private static boolean isSymbolInArray(char symbol, char[] array) {
        if (array == null)
            throw new IllegalArgumentException();

        for (char c : array) {
            if (c == symbol)
                return true;
        }
        return false;
    }
    private static int indexOf(char symbol, char[] array) {
        if (array == null)
            throw new IllegalArgumentException();

        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == symbol)
                index = i;
        }
        return index;
    }

}
