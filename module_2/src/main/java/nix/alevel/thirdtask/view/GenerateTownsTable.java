package nix.alevel.thirdtask.view;


import nix.alevel.thirdtask.entity.Vertex;

import java.util.List;

public class GenerateTownsTable {
    public static void generateTable(List<Vertex> inputDates){

        System.out.println("+----+---------------------------+"); //29
        System.out.println("+ ID |           Towns           |");
        System.out.println("+--------------------------------+");
        for (int i = 0; i < inputDates.size(); i++) {
            String s = "|  " +inputDates.get(i).getName();
            System.out.printf("| %2d %3s %"+(28 -s.length())+"s\n",inputDates.get(i).getId(), s, "|");
            System.out.println("+--------------------------------+");
        }
    }
}
