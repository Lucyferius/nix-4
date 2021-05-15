package nix.alevel.firsttask.controller;

import nix.alevel.filesystem.MyFileReader;
import nix.alevel.filesystem.MyFileWriter;
import nix.alevel.firsttask.util.DateFormatter;
import nix.alevel.firsttask.view.GenerateComparingTable;

import java.util.List;

public class RunFirstTask {
    private static final String inputFile = "resourceFiles/firstTask/input.txt";
    private static final String outputFile = "resultFiles/firstTask/output.txt";
    public static void runFirstTask() {
        MyFileReader fileReader = new MyFileReader(inputFile);
        List<String> dates = fileReader.readAll();
        List<String> result = DateFormatter.findPermittedDates(dates);
        MyFileWriter myFileWriter = new MyFileWriter(outputFile);
        myFileWriter.writeAll(result);
        GenerateComparingTable.generateTable(dates, result);
    }
}
