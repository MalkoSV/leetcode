package leetcode.mals.tasks.prefixtree;

public class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public int sum = 0;
    public boolean isWord;
}
