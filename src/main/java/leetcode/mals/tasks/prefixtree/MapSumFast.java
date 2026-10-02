package leetcode.mals.tasks.prefixtree;

import java.util.HashMap;
import java.util.Map;

public class MapSumFast {

    public Map<String, Integer> map = new HashMap<>();
    private final TrieNode root = new TrieNode();

    public void insert(String key, int val) {
        TrieNode current = root;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;

        map.put(key, val);
    }

    public int sum(String prefix) {

        int[] nodes = collectPrefixNodeIndexes(prefix);

        if (nodes == null) {
            return 0;
        }

        int sum = 0;

        for (String word : map.keySet()) {
            boolean isSuccess = true;

            if (word.length() < prefix.length()) {
                continue;
            }

            TrieNode currentNode = root;

            for (int i = 0; i < prefix.length(); i++) {
                currentNode = currentNode.children[nodes[i]];

                if (currentNode == null || word.charAt(i) - 'a' != nodes[i]) {
                    isSuccess = false;
                    break;
                }
            }

            if (currentNode != null && isSuccess) {
                sum += map.get(word);
            }
        }

        return sum;
    }

    private int[] collectPrefixNodeIndexes(String prefix) {
        int[] nodes = new int[prefix.length()];

        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {

            if (currentNode == null) {
                return null;
            }

            int index = prefix.charAt(i) - 'a';
            nodes[i] = index;
            currentNode = currentNode.children[index];
        }

        return nodes;
    }

}
