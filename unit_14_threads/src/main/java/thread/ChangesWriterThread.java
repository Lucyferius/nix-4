package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChangesWriterThread implements Runnable {
    private String fileName;

    private Boolean running = true;

    private final StringBuffer inputBuf = new StringBuffer();

    private final StringBuffer oldInputBuf = new StringBuffer();

    public ChangesWriterThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!inputBuf.toString().equals(oldInputBuf.toString())) {
                oldInputBuf.delete(0, oldInputBuf.length());
                oldInputBuf.append(inputBuf);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(inputBuf.toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void changeValue(String newSting) {
        inputBuf.append(newSting);
    }
    public void stop(){
        running = false;
    }
}
