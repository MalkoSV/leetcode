package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WordLadder2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int endWordIndex = wordList.indexOf(endWord);
        if (endWordIndex == -1) {
            return 0;
        }

        int beginWordIndex = wordList.indexOf(beginWord);

        if (beginWordIndex == -1) {
            wordList.add(beginWord);
            beginWordIndex = wordList.size() - 1;
        }

        return bfs(beginWordIndex, endWordIndex, buildGraph(wordList));
    }

    public List<Integer>[] buildGraph(List<String> wordList) {

        List<Integer>[] neighbors = new List[wordList.size()];

        for (int i = 0; i < neighbors.length; i++) {
            neighbors[i] = new ArrayList<>();
        }

        for (int i = 0; i < wordList.size() - 1; i++) {
            String word1 = wordList.get(i);

            for (int j = i + 1; j < wordList.size(); j++) {
                String word2 = wordList.get(j);

                if (word2.length() != word1.length()) {
                    continue;
                }

                int notSame = 0;

                for (int k = 0; k < word1.length(); k++) {
                    if (word1.charAt(k) != word2.charAt(k)) {
                        notSame++;
                    }

                    if (notSame > 1) {
                        break;
                    }
                }

                if (notSame == 1) {
                    neighbors[i].add(j);
                    neighbors[j].add(i);
                }
            }
        }
        return neighbors;
    }

    private int bfs(int begin, int end, List<Integer>[] graph) {
        int[] visited = new int[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 1;

        queue.offer(begin);
        visited[begin] = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            for (int i = 0; i < size; i++) {
                int index = queue.poll();

                List<Integer> neighbors = graph[index];

                for (int ng : neighbors) {
                    if (visited[ng] == 0) {
                        if (ng == end) {
                            return count;
                        }
                        queue.offer(ng);
                        visited[ng] = 1;
                    }
                }
            }
        }

        return 0;
    }
}
