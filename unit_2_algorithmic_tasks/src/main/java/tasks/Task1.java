package tasks;

import java.io.BufferedReader;
import java.io.IOException;

public class Task1 {
    public static void findSumOfNumbers(BufferedReader bufferedReader) throws IOException {
        System.out.println("Введите строку: ");
        String title = bufferedReader.readLine();
        String[] parts = title.split("[^0-9]");
        int sum = 0;
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals("")) {
                System.out.println("Найденное число: "+" "+parts[i]+" ");
                sum+= Integer.parseInt(parts[i]);
            }
        }
        System.out.println("Сумма всех чисел = "+sum);
    }
}
