package nix.alevel.util;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String string = scanner.nextLine();
            if(CheckOpenedClosedBracketsInLine.checkClosedBracketsInLine(string))
            System.out.println("Can be");
            else System.out.println("Cnn`t be");
        }
    }
}
