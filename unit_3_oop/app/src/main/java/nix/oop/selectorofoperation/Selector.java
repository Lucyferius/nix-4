package nix.oop.selectorofoperation;

import nix.oop.CalculatorService;
import nix.oop.ConsoleHelperService;
import nix.oop.factory.CalculatorFactory;
import nix.oop.factory.ConsoleFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class Selector {
    private CalculatorService calculator = CalculatorFactory.create();
    private ConsoleHelperService consoleHelper = ConsoleFactory.create();

    public void sum() throws IOException {
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.sum(a, b));
    }
    public void subtract() throws IOException{
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.subtraction(a, b));
    }
    public void multiple() throws IOException{
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.multiplication(a, b));
    }
    public void divide() throws IOException {
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.division(a, b));
    }
    public void findMinValue() throws IOException {
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.findMin(a, b));
    }
    public void findMaxValue() throws IOException {
        BigDecimal a = consoleHelper.readDecimal();
        BigDecimal b = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.findMax(a, b));
    }
    public void findAbs() throws IOException {
        BigDecimal a = consoleHelper.readDecimal();
        consoleHelper.writeResult(calculator.findAbs(a));
    }
    public void myMenu() {
        consoleHelper.writeMessage("1. Sum");
        consoleHelper.writeMessage("2. Subtraction");
        consoleHelper.writeMessage("3. Multiplication");
        consoleHelper.writeMessage("4. Division");
        consoleHelper.writeMessage("5. Find min of two numbers");
        consoleHelper.writeMessage("6. Find max of two numbers");
        consoleHelper.writeMessage("7. Find abs()");
        consoleHelper.writeMessage("0. Exit");
    }

}
