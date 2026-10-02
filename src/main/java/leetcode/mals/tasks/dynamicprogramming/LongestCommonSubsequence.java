package leetcode.mals.tasks.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence1(String text1, String text2) {
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
                result.add(positions);
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
            subsequence = merge(positionsInText1.get(i), subsequence);
        }

        return subsequence.size();
    }

    private List<Integer> merge(List<Integer> forAdding, List<Integer> originalSubsequence) {
        if (forAdding.isEmpty()) {
            return originalSubsequence;
        }

        List<Integer> result = new ArrayList<>(originalSubsequence);
        int subsequenceSize = originalSubsequence.size();
        int forAddingSize = forAdding.size();
        int forAddingIdx = 0;
        int replacedIdx = -1;

        int subsequenceIdx = searchInsert(originalSubsequence, forAdding.get(0));

        if (subsequenceIdx == subsequenceSize) {
            result.add(forAdding.get(0));
            return result;
        }

        while (forAddingIdx < forAddingSize) {
            int newValue = forAdding.get(forAddingIdx);

            if (subsequenceIdx == subsequenceSize) {
                if (newValue > originalSubsequence.get(subsequenceSize - 1)) {
                    result.add(newValue);
                    break;
                }

                if (subsequenceSize > 1 && newValue > originalSubsequence.get(subsequenceSize - 2) && replacedIdx != subsequenceSize - 1) {
                    result.set(subsequenceSize - 1, newValue);
                    replacedIdx = subsequenceSize - 1;
                }

                forAddingIdx++;
                continue;

            }

            int originalValue = originalSubsequence.get(subsequenceIdx);

            if (newValue <= originalValue) {
                if ((subsequenceIdx == 0 || newValue > originalSubsequence.get(subsequenceIdx - 1)) && subsequenceIdx != replacedIdx) {
                    result.set(subsequenceIdx, newValue);
                    replacedIdx = subsequenceIdx;
                    forAddingIdx++;
                    subsequenceIdx++;
                    continue;
                } else {
                    forAddingIdx++;
                    continue;
                }
            } else {
                if (subsequenceIdx == subsequenceSize - 1) {
                    result.add(newValue);
                    break;
                }
            }

            subsequenceIdx++;
        }

        return result;
    }

    private int searchInsert(List<Integer> list, int target) {
        return binarySearch(list, 0, list.size() - 1, target);
    }

    private int binarySearch(List<Integer> list, int left, int right, int target) {

        if (left == right) {
            if (left == list.size() - 1 && target > list.get(left)) {
                return list.size();
            }
            return left;
        }

        int i = left + (right - left) / 2;

        if (target <= list.get(i)) {
            return binarySearch(list, left, i , target);
        } else {
            return binarySearch(list, i + 1, right, target);
        }
    }

    private List<Integer> mergeLists(List<Integer> listForAdding, List<Integer> originalSubsequence) {
        if (listForAdding.isEmpty()) {
            return originalSubsequence;
        }

        System.out.println(originalSubsequence);

        List<Integer> result = new ArrayList<>(originalSubsequence);
        int subsequenceSize = originalSubsequence.size();
        int replacedIndex = -1;
        for (int numForAdding : listForAdding) {
            int insertIndex = searchInsert(originalSubsequence, numForAdding);
            if (insertIndex == subsequenceSize) {
                result.add(numForAdding);
                return result;
            }
            if (insertIndex != replacedIndex && originalSubsequence.get(insertIndex) != numForAdding) {
                result.set(insertIndex, numForAdding);
                replacedIndex = insertIndex;
            }
        }
        return result;
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int[] dp = new int[text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            int diagonal = 0;

            for (int j = 1; j <= text2.length(); j++) {
                int previous = dp[j];

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = diagonal + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                diagonal = previous;
            }
        }

        return dp[text2.length()];
    }
}
