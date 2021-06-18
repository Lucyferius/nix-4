package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ChangesWriterThread writerThread = new ChangesWriterThread("output.txt");
        new Thread(writerThread).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter the line: ");
                String readLine = reader.readLine();
                if (readLine.equals("quit")) {
                    System.out.println("exit");
                    break;
                } else writerThread.changeValue(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writerThread.stop();
    }
}
