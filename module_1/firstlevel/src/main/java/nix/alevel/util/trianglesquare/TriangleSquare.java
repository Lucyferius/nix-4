package nix.alevel.util.trianglesquare;

public class TriangleSquare {
    public static double calculateTriangleSquare(Point a,  Point b, Point c){
        double square = Math.abs((a.getX()-c.getX())*(b.getY()-a.getY())-
                (a.getX()-b.getX())*(c.getY()-a.getY()))*0.5;
        if (square==0) System.out.println("Such a triangle does not exist. Try again please...");
        return square;
    }
}
