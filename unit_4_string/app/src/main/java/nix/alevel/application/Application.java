package nix.alevel.application;

import nix.alevel.ConsoleHelperService;
import nix.alevel.factory.ConsoleFactory;
import nix.alevel.setofoperations.SetOfOperations;

import java.io.IOException;

public class Application {
    public void run() throws IOException {
        ConsoleHelperService consoleHelper = ConsoleFactory.create();
        consoleHelper.writeMessage("Begin of program\n");
        SetOfOperations setOfOperations = new SetOfOperations();

        while (true) {
            setOfOperations.menu();
            switch (setOfOperations.chooseFromMenu()) {
                case 1: {
                    setOfOperations.reverseAllString();
                    break;
                }
                case 2: {
                    setOfOperations.reverseSubstring();
                    break;
                }
                case 3: {
                    setOfOperations.reverseBetweenTwoIndexes();
                    break;
                }
                case 0: {
                    System.exit(0);
                }
            }
        }
    }
}
