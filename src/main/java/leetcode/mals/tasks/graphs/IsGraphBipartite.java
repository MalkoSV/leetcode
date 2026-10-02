package leetcode.mals.tasks.graphs;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int node, int parentColor, int[][] graph, int[] color) {
        color[node] = -parentColor;

        for (int n : graph[node]) {
            if (color[n] == color[node]) {
                return false;
            }
            if (color[n] == 0) {
                if (!dfs(n, color[node], graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }
}
