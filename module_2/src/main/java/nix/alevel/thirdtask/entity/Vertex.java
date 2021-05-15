package nix.alevel.thirdtask.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vertex implements Comparable<Vertex>
{
    private String name;
    private int id;
    private Edge[] adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
    public Vertex(String name, int id){
        this.name = name;
        this.id = id;
    }
    public Vertex(){}
}
