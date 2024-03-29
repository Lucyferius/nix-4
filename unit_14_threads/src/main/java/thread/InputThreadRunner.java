package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputThreadRunner {
    public static void main(String[] args) {
        ChangesWriterThread writerThread = new ChangesWriterThread("output.txt");
        new Thread(writerThread).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try{
                System.out.println("Enter the line: ");
                String readLine = reader.readLine();
                if (readLine.trim().equals("quit")) {
                    System.out.println("exit");
                    break;
                } else writerThread.changeValue(readLine);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        writerThread.stop();
    }
}
