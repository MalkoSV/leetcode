package leetcode.mals.tasks.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == '1') {
                    int key = toIndex(i, j, grid[i].length);
                    List<Integer> neighbors = getNeighbors(grid, i, j);

                    graph.put(key, neighbors);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();

        int total = graph.size();
        int result = 0;

        while (total > 0) {
            int node =  graph.entrySet().iterator().next().getKey();
            traverse(node, graph, visited);

            graph.keySet().removeAll(visited);
            visited.clear();

            total = graph.size();
            result++;
        }

    return result;
    }

    private void traverse(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);

        List<Integer> neighbors = graph.get(node);

        for (Integer n : neighbors) {

            if (!visited.contains(n)) {
                traverse(n, graph, visited);
            }
        }
    }


    private List<Integer> getNeighbors(char[][] grid, int i, int j) {
        List<Integer> neighbors = new ArrayList<>();

        if (i > 0 && grid[i - 1][j] == '1') {
            neighbors.add(toIndex(i - 1, j, grid[i].length));
        }

        if (j > 0 && grid[i][j - 1] == '1') {
            neighbors.add(toIndex(i, j - 1, grid[i].length));
        }

        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') {
            neighbors.add(toIndex(i, j + 1, grid[i].length));
        }

        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            neighbors.add(toIndex(i + 1, j, grid[i].length));
        }

        return neighbors;
    }

    private int toIndex(int i, int j, int n) {
        return i * n + j;
    }


}
