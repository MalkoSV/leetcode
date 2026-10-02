package leetcode.mals.tasks.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints2 {

    public int minCostConnectPoints(int[][] points) {

        int[] visited = new int[points.length];
        int[] minDistance = new int[points.length];

        visited[0] = 1;
        int count = 1;

        for (int i = 0; i < points.length; i++) {
            minDistance[i] = calculateManhattanDistance(points[i], points[0]);
        }

        int cost = 0;

        while (count < points.length) {
            int newPoint = findMin(minDistance, visited);
            cost += minDistance[newPoint];
            visited[newPoint] = 1;
            recalcMinDistance(newPoint, minDistance, points, visited);

            count++;
        }

        return cost;
    }

    private int calculateManhattanDistance(int[] point1, int[] point2) {
        return Math.abs(point2[0] - point1[0]) + Math.abs(point2[1] - point1[1]);
    }

    private void recalcMinDistance(int addedPoint, int[] minDistance, int[][] points, int[] visited) {
        for (int i = 0; i < points.length; i++) {
            if (visited[i] == 0) {
                int distanceFromNewPoint = calculateManhattanDistance(points[i], points[addedPoint]);

                minDistance[i] = Math.min(distanceFromNewPoint, minDistance[i]);
            }
        }
    }

    private int findMin(int[] minDistance, int[] visited) {
        int index = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < minDistance.length; i++) {
            if (minDistance[i] < min && visited[i] == 0) {
                min = minDistance[i];
                index = i;
            }
        }
        return index;
    }

}
