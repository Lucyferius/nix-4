package nix.alevel.controller;

import nix.alevel.ConsoleHelper;
import nix.alevel.util.GameOfLife;

import java.io.IOException;

public class GameOfLiveController {
    public static void run() throws IOException {
        ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
        boolean t = true;
        while (t){
            System.out.println("\t1. Begin playing\n" +
                    "\t0. Exit");
            switch (consoleHelper.readInteger()){
                case 1:{
                    System.out.println("\tEnter size of matrix \n\t" +
                            "(recommended: minimum 10x10)\n");
                    System.out.print("rows: ");
                    int rows = consoleHelper.readInteger();
                    System.out.print("columns: ");
                    int columns = consoleHelper.readInteger();
                    GameOfLife gameOfLife = new GameOfLife(rows, columns);
                    gameOfLife.printBoard(gameOfLife.getBoard());
                    int [][] heir = gameOfLife.nextGeneration(gameOfLife.getBoard());
                    gameOfLife.printBoard(heir);
                    boolean g = true;
                    while (g){
                        System.out.println("If you want to continue press -> 1, " +
                                "\n\t\t\texit  -> 0");
                        int choice = consoleHelper.readInteger();
                        if(choice == 0 || gameOfLife.allIsDead(heir)) g = false;
                        else if(choice == 1){
                            heir = gameOfLife.nextGeneration(heir);
                            gameOfLife.printBoard(heir);
                        }
                    }

                    break;
                }
                case 0: t = false;
            }
        }
    }
}
