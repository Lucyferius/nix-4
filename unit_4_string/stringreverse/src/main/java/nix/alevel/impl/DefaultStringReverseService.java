package nix.alevel.impl;

import nix.alevel.StringReverseService;

public class DefaultStringReverseService implements StringReverseService {
    String sting;
    @Override
    public String reverse(String src) {
        String stringsWithoutBlanks [] = src.split("\\s");
        StringBuilder reverseString = new StringBuilder();
        StringBuilder tmpResult = new StringBuilder();
        for (String s: stringsWithoutBlanks) {
            tmpResult.setLength(0);
            for (int i = s.length()-1; i>=0; i--) {
                tmpResult.append(s.charAt(i));
            }
            reverseString.append(tmpResult).append(" ");
        }
        return reverseString.toString().trim();
    }

    @Override
    public String reverse(String src, String dest) {
        if(src.contains(dest)){
        String reverse = reverse(dest);
        return src.replaceAll(dest, reverse);
        }
        else return "The entered string has not this substring, check your input ant try again.";
    }
    private int indexOfPreviousChar(String src, String dest){
        return src.indexOf(dest);
    }
    @Override
    public String reverse(String src, int firstIndex, int lastIndex) {
        if(correctInput(src, firstIndex, lastIndex)){
            String dest = src.substring(firstIndex,lastIndex+1);
            if(src.charAt(indexOfPreviousChar(src,dest)) == ' '){
                return src.substring(0, firstIndex+1) + reverse(dest);
            }else return src.replaceAll(dest, reverse(dest));
        }
        throw new ArrayIndexOutOfBoundsException("Index, that you enter, is incorrect.");
    }
    private boolean correctInput(String src, int firstIndex, int lastIndex){
        return (firstIndex < lastIndex && firstIndex < src.length() && firstIndex >= 0 && lastIndex < src.length());
    }
}
