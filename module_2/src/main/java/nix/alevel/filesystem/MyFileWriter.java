package nix.alevel.filesystem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyFileWriter {
    private String fileName;
    public MyFileWriter(String fileName){
        this.fileName = fileName;
    }
    public void writeAll(List<String> text){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
            for (String value : text) {
                bufferedWriter.write(value + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAll(String text){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
                bufferedWriter.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
