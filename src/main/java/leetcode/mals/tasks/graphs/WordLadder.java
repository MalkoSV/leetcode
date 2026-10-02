package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int endWordIndex = wordList.indexOf(endWord);
        if (endWordIndex == -1) {
            return 0;
        }

        return bfs(beginWord, endWord, wordList);
    }

    public List<String> getNeighbors(
            List<String> words,
            List<String> wordList,
            int[] visited
    ) {

        List<String> neighbors = new ArrayList<>();

        for (String wrd : words) {

            for (int i = 0; i < wordList.size(); i++) {
                String word1 = wordList.get(i);

                if (wrd.length() != word1.length() || visited[i] == 1) {
                    continue;
                }

                int notSame = 0;

                for (int k = 0; k < wrd.length(); k++) {
                    if (wrd.charAt(k) != word1.charAt(k)) {
                        notSame++;
                    }

                    if (notSame > 1) {
                        break;
                    }
                }

                if (notSame == 1) {
                    neighbors.add(word1);
                    visited[i] = 1;
                }
            }
        }
        return neighbors;
    }

    private int bfs(
            String beginWord,
            String endWord,
            List<String> wordList
    ) {
        int[] visited = new int[wordList.size()];
        Deque<String> queue = new ArrayDeque<>();
        int count = 1;

        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            List<String> root = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                root.add(queue.poll());
            }

            List<String> neighbors = getNeighbors(root, wordList, visited);

            for (String ng : neighbors) {
                if (ng.equals(endWord)) {
                    return count;
                }
                queue.offer(ng);
            }
        }

        return 0;
    }
}
