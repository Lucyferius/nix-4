package nix.alevel.util.knightmove;

public class KnightPosition {
    private int row;
    private int column;

    public KnightPosition(int row, int col){
        this.row = row;
        this.column =col;

    }
    //private int check
    public int getColumn(){
        return this.column;
    }
    public int getRow(){
        return this.row;
    }
}
