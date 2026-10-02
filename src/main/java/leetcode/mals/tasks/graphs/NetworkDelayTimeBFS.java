package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDelayTimeBFS {

    public int networkDelayTime(int[][] times, int n, int k) {
        if (times.length == 0) {
            return -1;
        }

        Map<Integer, List<Edge>> edges = new HashMap<>();
        Map<Integer, Integer> visited = new HashMap<>();
        Deque<Integer> queue = new ArrayDeque<>();

        for (int[] edge : times) {
            edges.computeIfAbsent(edge[0], l -> new ArrayList<>())
                    .add(new Edge(edge[1], edge[2]));
        }

        queue.offer(k);
        visited.put(k, 0);

        while (!queue.isEmpty()) {

            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                int currentNode = queue.poll();
                List<Edge> nodeEdges = edges.get(currentNode);

                if (nodeEdges != null) {
                    for (Edge e : nodeEdges) {
                        int sumWeight = visited.get(currentNode) + e.weight;

                        if (!visited.containsKey(e.node) || sumWeight < visited.get(e.node)) {
                            visited.put(e.node, sumWeight);
                            queue.offer(e.node);
                        }
                    }
                }
            }
        }

        if (visited.size() == n) {
            return Collections.max(visited.values());
        }

        return -1;
    }

    record Edge(int node, int weight) {}

}
