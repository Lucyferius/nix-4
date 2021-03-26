package nix.alevel.util;

public class GameOfLife {
    private final int row;
    private final int col;
    private int[][] board;
    private int[][] nextGeneration = null;
    public GameOfLife(int rowsInMatrix, int columnsInMatrix){
        if(rowsInMatrix<1|| columnsInMatrix<1 ) throw new IllegalArgumentException("Illegal row/column value" + "rows = "+ rowsInMatrix + " columns = " + columnsInMatrix);
        this.row = rowsInMatrix;
        this.col = columnsInMatrix;
        this.board = generateBoard();
    }

    public int[][] getBoard(){
        return board;
    }
    private int[][] generateBoard(){
        board = new int[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++)
                board[i][j] = (int) (Math.random() *2);
        }
        return board;
    }
    public void printBoard(int[][]board){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }
    public boolean allIsDead(int [][] board){
        int size = row * col;
        int count = 0;
        for (int i =0; i<row; i++){
            for (int j =0; j<col; j++){
                if(board[i][j] == 0) count++;
            }
        }
        return count == size;
    }
    public int[][] nextGeneration(int [][] board) {
        int[][] nextGeneration = new int[row][col];
        for (int l = 0; l < row; l++) {
            for (int m = 0; m < col; m++) {
                int aliveNeighbours = countLiveNeighbors(l, m, board);

                if ((board[l][m] == 1) && (aliveNeighbours < 2))
                    nextGeneration[l][m] = 0;
                else if ((board[l][m] == 1) && (aliveNeighbours > 3))
                    nextGeneration[l][m] = 0;
                else if ((board[l][m] == 0) && (aliveNeighbours == 3))
                    nextGeneration[l][m] = 1;
                else
                    nextGeneration[l][m] = board[l][m];
            }
        }
        return nextGeneration;
    }
    private int countLiveNeighbors(int row, int col, int[][] board){
        int aliveNeighbours = 0;
        for (int i = row-1; i<=row+1; i++){
            for (int j = col-1; j<=col+1; j++){
                aliveNeighbours += getCell(i, j, board);
            }
        }
        aliveNeighbours -= board[row][col];
        return aliveNeighbours;
    }
    private int getCell(int i, int j, int[][]board) {
        if (i < 0 || i >= row)
            return 0;
        if (j < 0 || j >= col)
            return 0;
        return board[i][j];
    }
}
