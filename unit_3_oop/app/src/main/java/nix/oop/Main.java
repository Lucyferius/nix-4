package nix.oop;

import nix.oop.application.Application;
import nix.oop.factory.CalculatorFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        try {
            app.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
