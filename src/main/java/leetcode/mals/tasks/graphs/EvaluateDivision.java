package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries
    ) {
        Map<String, List<String>> children = new HashMap<>();
        Map<List<String>, Double> division = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> pair = equations.get(i);
            String vertex1 = pair.get(0);
            String vertex2 = pair.get(1);

            children.putIfAbsent(vertex1, new ArrayList<>());
            children.get(vertex1).add(vertex2);

            children.putIfAbsent(vertex2, new ArrayList<>());
            children.get(vertex2).add(vertex1);

            division.put(pair, values[i]);
            division.put(List.of(vertex2, vertex1), 1 / values[i]);
        }

        int size = queries.size();
        double[] result = new double[size];

        for (int i = 0; i < size; i++) {

            List<String> query = queries.get(i);

            if (query.get(0).equals(query.get(1))
                    && children.containsKey(query.get(0))
            ) {
                result[i] = 1;
                continue;
            }

            Deque<String> queue = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();

            if (buildPath(query, children, visited, queue)) {
                double value = 1;

                while (queue.size() > 1) {
                    value = value * division.get(List.of(queue.removeFirst(), queue.getFirst()));
                }
                result[i] = value;
            } else {
                result[i] = -1;
            }
        }

        return result;
    }

    private  boolean buildPath(
            List<String> pair,
            Map<String, List<String>> children,
            Set<String> visited,
            Deque<String> queue
    ) {

        String first = pair.get(0);
        String last = pair.get(1);

        queue.offer(first);
        visited.add(first);

        List<String> list = children.get(first);

        if (list == null) {
            return false;
        }

        for (String child : list) {
            if (!visited.contains(child)) {
                if (last.equals(child)) {
                    queue.offer(child);
                    return true;
                } else {
                    if (buildPath(List.of(child, last), children, visited, queue)) {
                        return true;
                    };
                }
            }
        }
        queue.removeLast();

        return false;
    }
}
