package leetcode.mals.tasks.prefixtree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapSum {

    public Map<String, Integer> map = new HashMap<>();
    private TrieNode root = new TrieNode();

    public void insert(String key, int val) {
        map.put(key, val);

        TrieNode current = root;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true;
    }

    public int sum(String prefix) {
        int sum = 0;
        TrieNode node = findLastNode(prefix);

        if (node == null) {
            return 0;
        }

        Set<String> keys = findAllWordsFromNode(node, prefix);

        for (String key : keys) {
            sum += map.get(key);
        }

        return sum;
    }

    private TrieNode findLastNode(String prefix) {
        TrieNode currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';

            if (currentNode.children[index] == null) {
                return null;
            }

            currentNode = currentNode.children[index];
        }

        return currentNode;
    }

    private Set<String> findAllWordsFromNode(TrieNode node, String prefix) {

        Set<String> words = new HashSet<>();

        if (node.isWord) {
            words.add(prefix);
        }

        for (int i = 0; i < node.children.length; i++) {
            TrieNode child = node.children[i];

            if (child != null) {
                char ch = (char) (i + 'a');
                String sub = prefix + String.valueOf(ch);

                words.addAll(findAllWordsFromNode(child, sub));
            }
        }
        return words;
    }

}
