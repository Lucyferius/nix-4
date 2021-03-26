package nix.alevel.controller;

import nix.alevel.ConsoleHelper;
import nix.alevel.util.CheckOpenedClosedBracketsInLine;

import java.io.IOException;

public class SecondLevelController {
    public static void run() throws IOException {
        ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
        boolean t = true;
        while (t){
            System.out.println("\t1. Enter the string to check brackets` order\n" +
                    "\t0. Exit");
            switch (consoleHelper.readInteger()){
                case 1:{
                    System.out.println("\tEnter a string, please: ");
                    if(CheckOpenedClosedBracketsInLine.checkClosedBracketsInLine(consoleHelper.readString()))
                        System.out.println("String is valid");
                    else System.out.println("String is invalid");
                    break;
                }
                case 0:{
                    t = false;
                }
            }
        }
    }
}
