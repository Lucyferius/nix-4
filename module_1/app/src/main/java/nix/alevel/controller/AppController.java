package nix.alevel.controller;

import nix.alevel.ConsoleHelper;

import java.io.IOException;

public class AppController {
    public void run () throws IOException {
        ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
        while (true){
            System.out.println("1. First level\n" +
                    "2. Second level\n" +
                    "3. Third level\n" +
                    "0. Exit");
            switch(consoleHelper.readInteger()){
                case 1:{
                    FirstLevelController.run();
                    break;
                }
                case 2:{
                    SecondLevelController.run();
                    break;
                }
                case 3:{
                    GameOfLiveController.run();
                    break;
                }
                case 0:{
                    System.exit(0);
                }

            }
        }
    }
}
