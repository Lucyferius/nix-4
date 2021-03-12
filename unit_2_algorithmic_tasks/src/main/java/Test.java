import tasks.Task1;
import tasks.Task2;
import tasks.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println();
            System.out.println("1. Задача 1");
            System.out.println("2. Задача 2");
            System.out.println("3. Задача 3");
            System.out.println("   0 Выход");
            switch (Integer.parseInt(bufferedReader.readLine())) {
                case 1: {
                    Task1.findSumOfNumbers(bufferedReader);
                    break;
                }
                case 2:{
                    Map<Character, Integer> map;
                    map = Task2.findMatchesAmongLetters(bufferedReader);
                    Task2.printMapOfLetters(map);
                    break;
                }
                case 3:{
                    Task3.math( Task3.enterTheNumber(bufferedReader));
                    break;
                }
                default:{
                    System.out.println("Неверный ввод");
                }
                case 0:{
                    System.exit(0);
                }
            }
        }
    }
}
