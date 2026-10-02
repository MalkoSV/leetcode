package leetcode.mals.tasks.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCommonSubsequenceMyVersion {

    public int longestCommonSubsequence(String text1, String text2) {
        List<List<Integer>> positionsInText1 = getPositionsInText1(text1, text2);

        return calculateLCS(positionsInText1);
    }

    private List<List<Integer>> getPositionsInText1(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();

        Map<Character, List<Integer>> chars = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < length2; i++) {
            char ch2 = text2.charAt(i);

            List<Integer> positions = chars.get(ch2);
            if (positions != null) {
                result.add(new ArrayList<>(positions));
            } else {
                List<Integer> newPositions = new ArrayList<>();

                for (int j = 0; j < length1; j++) {
                    if (text1.charAt(j) == ch2) {
                        newPositions.add(j);
                    }
                }
                result.add(newPositions);
                chars.put(ch2, newPositions);
            }
        }
        return result;
    }

    private int calculateLCS(List<List<Integer>> positionsInText1) {
        List<Integer> subsequence = new ArrayList<>();
        int start = 0;
        int size = positionsInText1.size();

        for (int i = 0; i < size; i++) {
            if  (!positionsInText1.get(i).isEmpty()) {
                subsequence.add(positionsInText1.get(i).get(0));
                start = i + 1;
                break;
            }
        }

        if (subsequence.isEmpty()) {
            return 0;
        }

        for (int i = start; i < size; i++) {
            subsequence = mergeLists(positionsInText1.get(i), subsequence);
        }

        return subsequence.size();
    }

    private List<Integer> mergeLists(List<Integer> listForAdding, List<Integer> originalSubsequence) {
        if (listForAdding.isEmpty()) {
            return originalSubsequence;
        }

        List<Integer> result = new ArrayList<>(originalSubsequence);
        int subsequenceSize = originalSubsequence.size();
        boolean isReplacedFirst = false;
        int replacedIndex = -1;

        for (int numForAdding : listForAdding) {
            if (numForAdding > originalSubsequence.get(subsequenceSize - 1)) {
                result.add(numForAdding);
                return result;
            }

            if (numForAdding < originalSubsequence.get(0) && !isReplacedFirst) {
                result.set(0, numForAdding);
                isReplacedFirst = true;
                continue;
            }

            for (int i = subsequenceSize - 2; i >= 0; i--) {
                if (numForAdding > originalSubsequence.get(i)) {
                    if (replacedIndex != i + 1) {
                        result.set(i + 1, numForAdding);
                        replacedIndex = i + 1;
                    }
                    break;
                }

                if (numForAdding == originalSubsequence.get(i)) {
                    break;
                }
            }
        }
        return result;
    }
}
