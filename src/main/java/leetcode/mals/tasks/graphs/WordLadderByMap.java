package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadderByMap {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String, Integer> wordMap = new HashMap<>();

        for (int i = 0; i < wordList.size(); i++) {
            wordMap.put(wordList.get(i), i);
        }

        if (!wordMap.containsKey(endWord)) {
            return 0;
        }

        return bfs(beginWord, endWord, wordMap);
    }

    public List<String> getNeighbors(
            String word,
            Map<String, Integer> wordMap,
            int[] visited
    ) {

        List<String> neighbors = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);

        for (int i = 0; i < word.length(); i++) {
            char original = word.charAt(i);

            for (int j = 0; j < 26; j++) {
                char newChar = (char) ('a' + j);

                if (newChar == original) {
                    continue;
                }

                sb.setCharAt(i, newChar);
                String candidate = sb.toString();

                int index = wordMap.getOrDefault(candidate, -1);
                if (index != -1 && visited[index] == 0) {
                    neighbors.add(candidate);
                    visited[index] = 1;
                }
            }
            sb.setCharAt(i, original);
        }

        return neighbors;
    }

    private int bfs(
            String beginWord,
            String endWord,
            Map<String, Integer> wordMap
    ) {
        int[] visited = new int[wordMap.size()];
        Deque<String> queue = new ArrayDeque<>();
        int count = 1;

        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            for (int i = 0; i < size; i++) {

                List<String> neighbors = getNeighbors(queue.poll(), wordMap, visited);

                for (String ng : neighbors) {
                    if (ng.equals(endWord)) {
                        return count;
                    }
                    queue.offer(ng);
                }
            }
        }

        return 0;
    }
}
