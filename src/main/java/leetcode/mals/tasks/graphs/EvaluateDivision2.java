package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision2 {

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries
    ) {
        Map<String, List<Edge>> neighbors = new HashMap<>();

        int size = queries.size();
        double[] result = new double[size];

        for (int i = 0; i < equations.size(); i++) {
            List<String> pair = equations.get(i);
            String vertex1 = pair.get(0);
            String vertex2 = pair.get(1);

            neighbors.computeIfAbsent(vertex1, k -> new ArrayList<>())
                    .add(new Edge(vertex2, values[i]));

            neighbors.computeIfAbsent(vertex2, k -> new ArrayList<>())
                    .add(new Edge(vertex1, 1.0 / values[i]));
        }

        for (int i = 0; i < size; i++) {

            List<String> query = queries.get(i);
            String first = query.get(0);
            String last = query.get(1);

            if (first.equals(last)
                    && neighbors.containsKey(first)
            ) {
                result[i] = 1;
                continue;
            }

            if (!neighbors.containsKey(first)
                    || !neighbors.containsKey(last)
            ) {
                result[i] = -1;
                continue;
            }

            Set<String> visited = new HashSet<>();
            double currentValue = 1.0;

            result[i] = calculatePath(first, last, neighbors, visited, currentValue);

        }

        return result;
    }

    private  double calculatePath(
            String node,
            String target,
            Map<String, List<Edge>> neighbors,
            Set<String> visited,
            double currentValue
    ){
        if (node.equals(target)) {
            return currentValue;
        }

        visited.add(node);

        for (Edge e : neighbors.get(node)) {
            if (!visited.contains(e.neighbor)) {
                double result = calculatePath(
                        e.neighbor,
                        target,
                        neighbors,
                        visited,
                        currentValue * e.weight);
                if (result != -1) {
                    return result;
                }
            }
        }

        return -1;
    }

    record Edge(String neighbor, double weight) {}
}
