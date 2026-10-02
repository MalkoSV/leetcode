package leetcode.mals.tasks.graphs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfIslands2 {

    public int numIslands(char[][] grid) {

        int result = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    traverse(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void traverse(char[][] grid, int i, int j) {
        if (
                i >= 0
                && i < grid.length
                && j >= 0
                && j < grid[i].length
                && grid[i][j] == '1'
        ) {

            grid[i][j] = '0';

            traverse(grid, i - 1, j);
            traverse(grid, i, j - 1);
            traverse(grid, i, j + 1);
            traverse(grid, i + 1, j);
        }
    }
}

