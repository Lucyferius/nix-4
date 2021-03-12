package tasks;

import java.io.BufferedReader;
import java.io.IOException;

public class Task3 {
    /*public static void main(String[] args) throws IOException {
       // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }*/
    public static int enterTheNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("Введите номер урока(от 1 до 10) ");
        return Integer.parseInt(bufferedReader.readLine());
    }
    public static void math(int numberOfLesson){
        int startOfDay = 540; //minutes i.e. 9:00
        int breakTime =((numberOfLesson)/2)*5 + ((numberOfLesson-1)/2)*15;
        int timeOfLessons = numberOfLesson * 45;
        int result = timeOfLessons + breakTime;
        int hours = (startOfDay + result)/60;
        int minutes = (startOfDay+result)-hours*60;
        System.out.println("Время окончания урока "+hours+":"+minutes);
    }
}
