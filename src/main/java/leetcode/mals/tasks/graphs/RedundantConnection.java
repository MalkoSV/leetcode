package leetcode.mals.tasks.graphs;

import java.util.HashMap;
import java.util.Map;


public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        Map<Integer, Integer> parents = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        int[] result = new int[0];

        for (int[] edge : edges) {
            parents.putIfAbsent(edge[0], edge[0]);
            parents.putIfAbsent(edge[1], edge[1]);

            size.putIfAbsent(edge[0], 1);
            size.putIfAbsent(edge[1], 1);

            if (!union(edge[0], edge[1], parents, size)) {
                result = edge;
            }
        }
        return result;
    }

    private boolean union(int node1,
                          int node2,
                          Map<Integer, Integer> parent,
                          Map<Integer, Integer> size
    ) {
        int root1 = find(node1, parent);
        int root2 = find(node2, parent);

        if (root1 == root2) {
            return false;
        }

        int size1 = size.get(root1);
        int size2 = size.get(root2);

        if (size1 < size2) {
            parent.put(root1, root2);
            size.put(root2, size1 + size2);
        } else {
            parent.put(root2, root1);
            size.put(root1, size1 + size2);
        }

        return true;
    }

    private int find(int node, Map<Integer, Integer> parent) {
        int nodeParent = parent.get(node);

        if (nodeParent == node) {
            return node;
        }

        int root = find(nodeParent, parent);
        parent.put(node, root);

        return root;
    }

}
