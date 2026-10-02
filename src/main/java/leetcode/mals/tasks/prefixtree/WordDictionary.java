package leetcode.mals.tasks.prefixtree;

public class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';


            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isWord = true;
    }

    public boolean search(String word) {
        return searchFromNode(word, 0, root);
    }


    private boolean searchFromNode(String word, int wordIndex, TrieNode node) {
        TrieNode current = node;

        for (int i = wordIndex; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch != '.') {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    return false;
                }

                current = current.children[index];

            } else {

                for (int j = 0; j < current.children.length; j++) {
                    if (current.children[j] != null) {
                        if (searchFromNode(word, i + 1, current.children[j])) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }

        return current.isWord;
    }
}
