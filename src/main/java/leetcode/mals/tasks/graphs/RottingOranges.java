package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {

        Deque<int[]> queue = new ArrayDeque<>();
        int orangeCount = 0;
        int visited = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }

                if (grid[i][j] != 0) {
                    orangeCount++;
                }
            }
        }

        if (orangeCount == 0) {
            return 0;
        }

        int minuteCount = -1;

        while (!queue.isEmpty()) {
            visited += queue.size();

            bfs(queue, grid);

            minuteCount++;
        }

        if (visited != orangeCount) {
            return -1;
        }

        return minuteCount;
    }

    private void bfs(Deque<int[]> queue, int[][] grid) {
        int level = queue.size();

        for (int k = 0; k < level; k++) {
            int[] pnt = queue.poll();

            int i = pnt[0];
            int j = pnt[1];

            if (j > 0 && grid[i][j - 1] == 1) {
                queue.offer(new int[]{i, j - 1});
                grid[i][j - 1] = 2;
            }

            if (i > 0 && grid[i - 1][j] == 1) {
                queue.offer(new int[]{i - 1, j});
                grid[i - 1][j] = 2;
            }

            if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
                queue.offer(new int[]{i, j + 1});
                grid[i][j + 1] = 2;
            }

            if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                queue.offer(new int[]{i + 1, j});
                grid[i + 1][j] = 2;
            }
        }
    }
}
