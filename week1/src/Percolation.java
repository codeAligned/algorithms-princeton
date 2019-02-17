import java.util.Arrays;

public class Percolation {

    // grid
    int[][] grid;

    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("n provided to initialise Percolation is less than or equal to zero.");
        } else {
            // this initializes to all zeroes upon calling
            grid = new int[n][n];
        }
    }

    // default getGrid returns entire array
    public void getGrid() {
        System.out.println(Arrays.deepToString(grid));
    }

    public void getGrid(int i, int j) {
        if (i <= 0 || j <= 0) {
            throw new IllegalArgumentException("i or j less than or equal zero.");
        }
        System.out.println(grid[i][j]);
    }
}
