package nix.alevel.thirdtask.contoller;

import nix.alevel.filesystem.MyFileReader;
import nix.alevel.filesystem.MyFileWriter;
import nix.alevel.thirdtask.entity.Vertex;
import nix.alevel.thirdtask.util.MinPathInGraphSearcher;
import nix.alevel.thirdtask.util.TownsReader;
import nix.alevel.thirdtask.view.GenerateTownsTable;

import java.util.List;

public class RunThirdTask {
    public static void run() {
        String inputFile = "resourceFiles/thirdTask/input.txt";
        String outputFile = "resultFiles/thirdTask/output.txt";
        List<Vertex> vertices = TownsReader.readVertices(inputFile);
        GenerateTownsTable.generateTable(vertices);
        String[][] tasks = TownsReader.readTask(vertices, inputFile);

        MinPathInGraphSearcher graphSearcher = new MinPathInGraphSearcher(vertices);
        StringBuilder determineDist = new StringBuilder();
        for (String[] task : tasks) {
            double dist = graphSearcher.determineShortestPath(task[0], task[1]);
            List<String> path = graphSearcher.showTheShortestPathWithTowns(task[0], task[1]);
            determineDist.append("Distance from ").append(task[0]).append(" to ").append(task[1]).append(" = ").append(dist);
            determineDist.append("\n").append("The shorted path from " + task[0] + " to " + task[1] + " is ");
            determineDist.append("[ ");
            for (int i = 0; i < path.size(); i++) {
                determineDist.append(path.get(i));
                if (i != path.size() - 1)
                    determineDist.append(" -> ");
            }
            determineDist.append(" ]\n\n");

            MyFileWriter myFileWriter = new MyFileWriter(outputFile);
            myFileWriter.writeAll(determineDist.toString());
        }
        MyFileReader myFileReader = new MyFileReader(outputFile);
        List<String> toConsole = myFileReader.readAll();
        System.out.println("\n\n Results from file: \n");
        toConsole.forEach(System.out::println);
    }
}
