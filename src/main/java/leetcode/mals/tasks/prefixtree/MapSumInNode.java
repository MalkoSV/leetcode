package leetcode.mals.tasks.prefixtree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapSumInNode {

    public Map<String, Integer> map = new HashMap<>();
    private final TrieNode root = new TrieNode();

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);

        TrieNode current = root;
        root.sum += delta;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
            current.sum += delta;
        }
        current.isWord = true;
        map.put(key, val);
    }

    public int sum(String prefix) {
        TrieNode lastNode = findLastNode(prefix);

        return lastNode != null ? lastNode.sum : 0;
    }

    private TrieNode findLastNode(String prefix) {

        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            currentNode = currentNode.children[index];

            if (currentNode == null) {
                return null;
            }
        }

        return currentNode;
    }

}
