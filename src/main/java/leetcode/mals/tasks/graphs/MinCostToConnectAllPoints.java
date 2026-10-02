package leetcode.mals.tasks.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {

        int[][] distance = new int[points.length][points.length];
        int[] visited = new int[points.length];

        int cost = 0;

        for (int i = 0; i < distance.length; i++) {
            for (int j = i; j < distance[i].length; j++) {
                distance[i][j] = calculateManhattanDistance(points[i], points[j]);
                distance[j][i] = distance[i][j];
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(ar -> ar[1]));

        visited[0] = 1;

        offerEdgesForPoint(0, distance, visited, queue);

        while (!queue.isEmpty()) {
            int[] edge = queue.poll();

            if (visited[edge[0]] == 0) {
                visited[edge[0]] = 1;
                offerEdgesForPoint(edge[0], distance, visited, queue);

                cost += edge[1];
            }
        }

        return cost;
    }

    private int calculateManhattanDistance(int[] points1, int[] points2) {
        return Math.abs(points2[0] - points1[0]) + Math.abs(points2[1] - points1[1]);
    }

    private void offerEdgesForPoint(
            int point,
            int[][] distance,
            int[] visited,
            PriorityQueue<int[]> queue
    ) {
        int[] dist = distance[point];
        for (int i = 0; i < dist.length; i++) {
            if (visited[i] == 0) {
                queue.offer(new int[]{i, dist[i]});
            }
        }
    }
}
