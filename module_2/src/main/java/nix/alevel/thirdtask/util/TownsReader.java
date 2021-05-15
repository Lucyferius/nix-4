package nix.alevel.thirdtask.util;

import nix.alevel.thirdtask.entity.Edge;
import nix.alevel.thirdtask.entity.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TownsReader {
    public static List<Vertex> readVertices(String fileName){
        int countOfTowns = 0;
        List<Vertex> vertices = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            try {
                String s = bufferedReader.readLine();
                try {
                    countOfTowns = Integer.parseInt(s);
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }

                for (int i = 0; i < countOfTowns; i++) {
                    String name = bufferedReader.readLine();
                    Vertex v = new Vertex(name, i+1);
                    int countOfAdjacencies = 0;
                    try {
                        countOfAdjacencies = Integer.parseInt(bufferedReader.readLine());
                    }catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                    Edge[] edgesOfVertex = new Edge[countOfAdjacencies];
                    for (int j = 0; j < countOfAdjacencies; j++) {
                        String idWeight = bufferedReader.readLine();
                        String a = idWeight.replace(" ", "/");
                        String[] regex = a.split("/");
                        try {
                            edgesOfVertex[j] = new Edge(Integer.parseInt(regex[0]), Integer.parseInt(regex[1]));
                        }catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    v.setAdjacencies(edgesOfVertex);
                    vertices.add(v);
                }
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }
    public static String[][] readTask(List<Vertex> vertices, String fileName){
        String [][] tasks = null;
        int count = 1 + vertices.size();
        for (Vertex v: vertices){
            count++;
            count +=v.getAdjacencies().length;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            for (int i = 0; i < count; i++) {
                bufferedReader.readLine();
            }
            try {
                int countOFTasks = Integer.parseInt(bufferedReader.readLine());
                tasks = new String[countOFTasks][2];
                for (int i = 0; i < countOFTasks; i++) {
                    String s = bufferedReader.readLine();
                    String a = s.replace(" ","/");
                    String[] arr = a.split("/");
                    tasks[i][0] = arr[0];
                    tasks[i][1] = arr[1];
                }

            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

}
