package Other;

import org.apache.commons.math3.distribution.NormalDistribution;

public class First {
    public First(){

        System.out.println("First is initialized");
        NormalDistribution normalDistribution = new NormalDistribution(10, 3);
        double randomValue = normalDistribution.sample();
        System.out.println(randomValue);
    }
}
