package leetcode.mals.tasks.prefixtree;

public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
                currentNode = currentNode.children[index];
        }

        currentNode.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = findLastNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {

        return findLastNode(prefix) != null;
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

}
