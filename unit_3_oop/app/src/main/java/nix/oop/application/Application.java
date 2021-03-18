package nix.oop.application;


import nix.oop.CalculatorService;
import nix.oop.ConsoleHelperService;
import nix.oop.factory.CalculatorFactory;
import nix.oop.factory.ConsoleFactory;
import nix.oop.selectorofoperation.Selector;

import java.io.IOException;

public class Application {
    public void run() throws IOException {
        ConsoleHelperService consoleHelper = ConsoleFactory.create();
        consoleHelper.writeMessage("Begin of program\n");
        Selector selector = new Selector();

        while (true) {
            selector.myMenu();
            switch (consoleHelper.readInteger()) {
                case 1: {
                    selector.sum();
                    break;
                }
                case 2: {
                    selector.subtract();
                    break;
                }
                case 3: {
                    selector.multiple();
                    break;
                }
                case 4: {
                    selector.divide();
                    break;
                }
                case 5: {
                    selector.findMinValue();
                    break;
                }
                case 6: {
                    selector.findMaxValue();
                    break;
                }
                case 7: {
                    selector.findAbs();
                    break;
                }
                case 0: {
                    consoleHelper.writeMessage("End of program\n");
                    System.exit(0);
                }
                default: {
                    consoleHelper.writeMessage("Неправильный ввод");
                }
            }
        }
    }
}
