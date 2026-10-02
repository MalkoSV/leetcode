package leetcode.mals.tasks.dynamicprogramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<Integer> set = new HashSet<>();

        return dfs(s, wordDict, 0, set);
    }

    private boolean dfs(
            String s,
            List<String> wordDict,
            int index,
            Set<Integer> set
    ) {

        if (set.contains(index)) {
            return false;
        }

        int strLength = s.length() - index;

        for (String word : wordDict) {
            int wordLength = word.length();

            if (wordLength > strLength) {
                continue;
            }

            if (s.startsWith(word, index)) {
                if (wordLength == strLength) {
                    return true;
                }

                boolean result = dfs(s, wordDict, index + wordLength, set);

                if (result) {
                    return true;
                } else {
                    set.add(index + wordLength);
                }
            }
        }
        return false;
    }

}
