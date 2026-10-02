package leetcode.mals.tasks.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    final int INF = Integer.MAX_VALUE / 2;

    public int networkDelayTime(int[][] times, int n, int k) {

        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[k] = 0;

        List<Edge>[] graph = new ArrayList[n + 1];

        for (int[] edge : times) {
            if (graph[edge[0]] == null) {
                graph[edge[0]] = new ArrayList<>();
            }
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::distance)
        );

        queue.offer(new Node(k, distance[k]));

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (current.distance > distance[current.value]) {
                continue;
            }

            List<Edge> edges = graph[current.value];

            if (edges != null) {
                for (Edge e : edges) {
                    int newDistance = current.distance + e.weight;

                    if (newDistance < distance[e.to]) {
                        distance[e.to] = newDistance;
                        queue.offer(new Node(e.to, newDistance));
                    }
                }
            }
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            if (distance[i] == INF) {
                return -1;
            }

            max = Math.max(max, distance[i]);
        }

        return max;
    }

    record Node(int value, int distance) {}
    record Edge(int to, int weight) {}

}
