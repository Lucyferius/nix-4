package nix.alevel.secondtask.controller;

import nix.alevel.filesystem.JsonReader;
import nix.alevel.filesystem.JsonWriter;
import nix.alevel.secondtask.util.UniqueNamesFinder;
import nix.alevel.secondtask.view.GenerateNamesTable;

import java.util.List;

public class RunSecondTask {
    private static final String inputFile = "resourceFiles/secondTask/input.json";
    private static final String outputFile = "resultFiles/secondTask/output.json";
    public static void run() {
        JsonReader jsonReader = new JsonReader();
        List<String> names =  jsonReader.read(inputFile);
        long start = System.currentTimeMillis();
        List<String> uniq = UniqueNamesFinder.findUniqueNamesInList(names);
        long end = System.currentTimeMillis();
        long diff = end - start;
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.write(outputFile, uniq);

        long startTwo = System.currentTimeMillis();
        uniq = UniqueNamesFinder.findUniqueNamesInList(names);
        long endTwo = System.currentTimeMillis();
        long diffTwo = endTwo - startTwo;

        long startTree = System.currentTimeMillis();
        String name = UniqueNamesFinder.findUniqueNameById(uniq,2);
        long endTree = System.currentTimeMillis();
        long diffTree = endTree - startTree;

        GenerateNamesTable.generateTable(names, uniq, diff, diffTwo, diffTree, name);
    }
}
