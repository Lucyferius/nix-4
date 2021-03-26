package nix.alevel.controller;

import nix.alevel.ConsoleHelper;
import nix.alevel.util.knightmove.KnightMoveCheck;
import nix.alevel.util.knightmove.KnightPosition;
import nix.alevel.util.trianglesquare.Point;
import nix.alevel.util.trianglesquare.TriangleSquare;
import nix.alevel.util.uniquenumbers.UniqueNumbersFinder;

import java.io.IOException;
import java.util.List;

public class FirstLevelController {
    public static ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
    public static void run() throws IOException {
        boolean allCycle = true;
        while (allCycle){
            System.out.println("\t1. Count the amount of unique numbers in array\n" +
                    "\t2. Check is it possible to move the knight in chess board\n" +
                    "\t3. Count the square of triangle with 3 coordinates\n" +
                    "\t0. Exit");
            System.out.println("Your choice:");
            switch (consoleHelper.readInteger()){
                case 1:{
                    System.out.println("Enter an array of the positive integer numbers \n(for example: 1 12 3 4)");
                    List<Integer> array = consoleHelper.makeIntegerArrayFromString
                            (consoleHelper.readString());
                    consoleHelper.printArray(array);
                    System.out.println("Amount of unique numbers: "+UniqueNumbersFinder.findUniqueNumbersInArray(array));
                    break;
                }
                case 2:{
                    boolean t = true;
                    int cycle =0;
                    KnightPosition lastPosition = null;
                    while (t) {
                        System.out.println("\t1.Enter positions\n\t0.Exit");
                        switch (consoleHelper.readInteger()) {
                            case 1: {
                                if(cycle==0){
                                    System.out.print("Enter the current position\nRow:");
                                    int currentRow = consoleHelper.readInteger();
                                    System.out.print("Column:");
                                    int currentColumn = consoleHelper.readInteger();

                                    KnightPosition pointA = new KnightPosition(currentRow, currentColumn);

                                    lastPosition = isItPossibleToMoveFromPointToPoint(pointA);
                                    cycle++;
                                }
                                else {
                                    System.out.println("Current: row " + lastPosition.getRow() + " column " + lastPosition.getColumn());

                                    lastPosition = isItPossibleToMoveFromPointToPoint(lastPosition);
                                }
                                break;
                            }

                            case 0: t=false;
                        }
                    }
                    break;
                }
                case 3:{
                    System.out.println("Enter the coordinates of each point...");
                    System.out.println("Square = "+ TriangleSquare.calculateTriangleSquare(enterTheCoordinates(1),enterTheCoordinates(2), enterTheCoordinates(3)));
                    break;
                }
                case 0: allCycle = false;
            }
        }
    }


    private static Point enterTheCoordinates(int numberOfPoint) throws IOException {
        System.out.print("Point "+numberOfPoint+" \tx: ");
        int x = consoleHelper.readInteger();
        System.out.print("\t\ty: ");
        int y = consoleHelper.readInteger();
        return new Point(x,y);
    }

    private static KnightPosition isItPossibleToMoveFromPointToPoint(KnightPosition pointA) throws IOException {
        System.out.print("Enter the position to move\nRow:");
        int positionToMoveRow = consoleHelper.readInteger();
        System.out.print("Column:");
        int positionToMoveColumn = consoleHelper.readInteger();

        KnightPosition pointB = new KnightPosition(positionToMoveRow, positionToMoveColumn);

        System.out.println("Move to: row " + pointB.getRow() + " column " + pointB.getColumn());
        boolean check = KnightMoveCheck.checkMovePossible(pointA, pointB);
        System.out.println("Is it possible to move: " + KnightMoveCheck.checkMovePossible(pointA, pointB));

        if(check) return pointB;
        else return pointA;
    }
}
