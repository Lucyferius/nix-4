package nix.alevel.util;

import nix.alevel.model.Location;
import nix.alevel.model.Route;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public class MinPathInGraphSearcher {
    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private List<Location> vertices;
    private List<Route> routes;
    public MinPathInGraphSearcher(List<Location> vertices, List<Route> routes){
        this.vertices = vertices;
        this.routes = routes;

        addVertices();
        addEdges();
    }
    private void addVertices (){
        for (Location vertex : vertices) {
            graph.addVertex(vertex.getId());
        }
    }
    private void addEdges(){
        for (Route route: routes){
            DefaultWeightedEdge edge = graph.addEdge(route.getIdFrom(), route.getIdTo());
            if (edge != null) {
                graph.setEdgeWeight(edge, route.getCost());
            }
        }
    }
    public int determineShortestPath(int from, int to){
        DijkstraShortestPath<Integer, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        return (int) dijkstraShortestPath.getPath(from, to).getWeight();
    }
}
