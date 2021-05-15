package nix.alevel.thirdtask.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Edge
{
    private int idTarget;
    private double weight;
    public Edge(int argTarget, double argWeight) {
        idTarget = argTarget;
        weight = argWeight;
    }
}
