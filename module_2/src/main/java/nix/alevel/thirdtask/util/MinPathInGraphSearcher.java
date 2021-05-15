package nix.alevel.thirdtask.util;

import nix.alevel.thirdtask.entity.Edge;
import nix.alevel.thirdtask.entity.Vertex;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public class MinPathInGraphSearcher {
    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private List<Vertex> vertices;
    public MinPathInGraphSearcher(List<Vertex> vertices){
        this.vertices = vertices;
        addVertices();
        addEdges();
    }
    private void addVertices (){
        for (Vertex vertex : vertices) {
            graph.addVertex(vertex.getName());
        }
    }
    private void addEdges(){
        for (Vertex v: vertices) {
            for (Edge e: v.getAdjacencies()) {
                DefaultWeightedEdge edge = graph.addEdge(v.getName(), vertices.get(e.getIdTarget() - 1).getName());
                if (edge != null) {
                    graph.setEdgeWeight(edge, e.getWeight());
                }
            }
        }
    }
    public List<String> showTheShortestPathWithTowns(String from, String to){
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        return dijkstraShortestPath.getPath(from, to).getVertexList();
    }
    public double determineShortestPath(String from, String to){
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        return dijkstraShortestPath.getPath(from, to).getWeight();
    }
}
